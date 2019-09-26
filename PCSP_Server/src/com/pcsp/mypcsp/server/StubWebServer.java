package com.pcsp.mypcsp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcsp.mypcsp.service.UserService;
import com.pcsp.mypcsp.stubwebserver.info.CarInfo;
import com.pcsp.mypcsp.vo.UserVO;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

class SharedObject {
	ArrayList<CarInfo> carInfoList;
	AndroidClients androidClients;
	PandaClients pandaClients;

	public SharedObject() {
		carInfoList = new ArrayList<CarInfo>();
		androidClients = new AndroidClients();
		pandaClients = new PandaClients();
	}
}

interface ClientThreadList {
	public YouJaClientRunnable findClient(String id);

	public void addClient(YouJaClientRunnable runnable);
}

class AndroidClients implements ClientThreadList {
	ArrayList<YouJaClientRunnable> androidThreadList = new ArrayList<YouJaClientRunnable>();

	@Override
	public YouJaClientRunnable findClient(String id) {
		for (YouJaClientRunnable a : androidThreadList) {
			if (id.equals(a.getID())) {
				return a;
			}
		}
		return null;
	}

	@Override
	public void addClient(YouJaClientRunnable runnable) {
		androidThreadList.add(runnable);
	}

}

class PandaClients implements ClientThreadList {
	ArrayList<YouJaClientRunnable> pandaThreadList = new ArrayList<YouJaClientRunnable>();

	@Override
	public YouJaClientRunnable findClient(String id) {
		for (YouJaClientRunnable a : pandaThreadList) {
			if (id.equals(a.getID())) {
				return a;
			}
		}
		return null;
	}

	@Override
	public void addClient(YouJaClientRunnable runnable) {
		pandaThreadList.add(runnable);

	}

}

class KeyRequest {
	String userId;
	String carNum;
	String KEY;

	KeyRequest() {

	};

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String makeKey() {
		// 키를 랜덤으로 10자리 수 만들기
		// 만든 키값을 암호화
		// RSA라이브러리 필요.
		KEY = "KEY";

		return KEY;
	}

	public void sendKey() {
		// 안드로이드와 라뗴판다에게 key값을 전해준다.
		// 안드로이드 같은 경우 이것을 요구한 socket에게
		// 라떼 판다의 경우 carNum에 맞는 소켓에게 key값을 전해줘야함

	}
}

class YouJaClientRunnable implements Runnable {
	Socket socket;
	BufferedReader br;
	PrintWriter out;
	String ID;
	CarInfo carInfo = new CarInfo();
	KeyRequest keyRequest = new KeyRequest();
	int clientType;
	final static int appType = 1;
	final static int carType = 2;
	SharedObject sharedObject;

	public void setType(String type) {
		if (type.equals("APP")) {
			clientType = appType;
		} else if (type.equals("CAR")) {
			clientType = carType;
		} else {

		}

	}

	YouJaClientRunnable(Socket socket, SharedObject sharedObject) {
		this.socket = socket;
		this.sharedObject = sharedObject;
		try {
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(isr);
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/*
	 * public void out(String str) { System.out.println("클라이언트에게 값을 넘겨줍니다. : " +
	 * str); out.println("KEYVALUE,"+str); out.flush();
	 * 
	 * } public void sendKeyValue() { String KEY = "KEY";
	 * System.out.println("클라이언트에게 키값을 넘겨줍니다. : " + KEY);
	 * out.println("KEYVALUE,"+KEY); out.flush(); }
	 */

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	@Override
	public void run() {
		String line;
		System.out.println("클라이언트 쓰래드가 돌고 있어요~");
		
		try {
			System.out.println("db접속 제발");
						
			while ((line = br.readLine()) != null) {
				System.out.println("클라이언트 데이터 받음 : " + line);
				// br.readLine() 이 null 인 경우 는 클라이언트가 접속을 끊었을때
				String receiveDateFromClient[] = line.split("/");
				String protocol = receiveDateFromClient[1];
				setType(receiveDateFromClient[0]);
				String data = receiveDateFromClient[2];

				if (protocol.equals("/@EXIT")) {
					break;// 가장 근접한 루프를 탈출
				}
				if (clientType == appType) {
					if(protocol.equals("USERLOGIN")) {
						String dataArr[] = data.split(",");
						String id = dataArr[0];
						String pw = dataArr[1];
						System.out.println("요청된 아이디와 비밀번호 : " + id + pw);
	
						UserService uService = new UserService();
						UserVO userVO = uService.getUser(id, pw);

						
						System.out.println(userVO.getUserNum());
						if (userVO.getUserNum() == 0) {
							out.println("ANDROID/LOGIN/FAIL");
							System.out.println(userVO.toString());
						} else {
							out.println("ANDROID/LOGIN/OK/" + userVO.toString());							
							System.out.println(userVO.toString());
						}
						out.flush();
					
						
					}
					else if (protocol.equals("CARREGISTER")) {

						String dataArr[] = data.split(",");
						carInfo.setCarNum(dataArr[2]);
						carInfo.setUserID(dataArr[0]);
						carInfo.setUserName(dataArr[1]);
					} else if (protocol.equals("CARNUM")) {
						ID = data;
					}

				} else if (clientType == carType) {
					if (protocol.equals("CARREGISTER")) {
						System.out.println(data);
						String dataArr[] = data.split(",");

					} else if (protocol.equals("KEYREQUEST")) {
						System.out.println(data);
						String dataArr[] = data.split(",");
						keyRequest.setUserId(dataArr[0]);
						keyRequest.setCarNum(dataArr[1]);
						// 안드로이드와 라뗴판다 둘다 키값을 줘야함.
						keyRequest.sendKey();
					} else if (protocol.equals("USERID")) {
						ID = data;
					} else if (protocol.equals("ADROID")) {
						clientType = appType;
					}
				}

			}
			// 자원 정리...

		} catch (IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

public class StubWebServer extends Application {

	TextArea textArea;
	Button startBtn, closeBtn;
	ExecutorService executorService = Executors.newCachedThreadPool();
	ServerSocket server;
	TextField textField;
	// List<EchoRunnable2> clients = new ArrayList<EchoRunnable2>();
	// Exam99_RoomManager roomManager = new Exam99_RoomManager();
	SharedObject sharedObject = new SharedObject();

	private void printMsg(String msg) {
		Platform.runLater(() -> {
			textArea.appendText(msg + "\n");
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		// SharedBox sharedBox = new SharedBox();

		textArea = new TextArea();
		textField = new TextField();
		textField.setPrefSize(400, 100);
		root.setCenter(textArea);

		startBtn = new Button("서버시작.");
		startBtn.setPrefSize(250, 100);
		startBtn.setOnAction(t -> {
			Runnable runnable = () -> {
				try {
					// 버튼을 누르면 서버 쪽에 server쪽에 socket접속을 시도.
					server = new ServerSocket(2206);
					printMsg("에코서버 시작! 2206");
					while (true) {
						printMsg("클라이언트 접속 대기\n");
						Socket s = server.accept(); // 여기에서 블락 클라이언트 올때 까지 -> 그래서 자바fx가 멈춤
						// 그래서 여기 대기하는 부분을 새로운 쓰래드로 만들어야함

						printMsg("클라이언트 접속 성공\n");
						// 클라이언트가 접속햇으니 쓰래드 만들고 쓰래드 시작.
						YouJaClientRunnable r = new YouJaClientRunnable(s, sharedObject);
						System.out.println("클라이언트 쓰래드 생성\n");
						// sharedBox.addClient(r);
						executorService.execute(r);
					}

				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			executorService.execute(runnable);
		});

		closeBtn = new Button("echo 서버 접속 해제");
		closeBtn.setPrefSize(250, 100);
		closeBtn.setOnAction(t -> {
			try {

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		FlowPane flowPane = new FlowPane();
		flowPane.setPrefSize(700, 50);
		flowPane.getChildren().add(startBtn);
		flowPane.getChildren().add(textField);
		flowPane.getChildren().add(closeBtn);
		root.setBottom(flowPane);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("서버입니다.");
		primaryStage.show();

	}

	public static void main(String[] args) {

		launch();
	}
}