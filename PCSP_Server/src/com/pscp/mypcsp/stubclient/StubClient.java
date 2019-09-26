package com.pscp.mypcsp.stubclient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class StubClient extends Application{

   TextArea textArea;
   TextField msgTf,idTf;
   Button startBtn , stopBtn;
   InputStreamReader isr;
   Socket socket;
   BufferedReader br;
   PrintWriter out;
   // 클라이언트 쓰래드는 1개만 만들어져요.
   // 쓰래드 pool을 쓸 경우 오버헤드
   ExecutorService executorService = Executors.newCachedThreadPool();
   
   private void printMsg(String msg) {
      Platform.runLater(()->{
         textArea.appendText(msg+"\n");
      });
   }
   // 사버로 부터 들어오는 메시지를 계속 받아서 화면에 출력하는 Thread
   class ReceiveRunnable implements Runnable{
      private BufferedReader br;
      
      public ReceiveRunnable(BufferedReader br) {
         this.br = br;
         printMsg("객체 생성됨");
      }
      
      @Override
      public void run() {
         String line = "";
         try {
            while((line = br.readLine())!= null) {
               printMsg(line);
            }
         }catch (Exception e) {
            
         }
         
      }
      
   }
   @Override
   public void start(Stage primaryStage) throws Exception {
   
      BorderPane root = new BorderPane();
      root.setPrefSize(700, 500); 

      textArea = new TextArea();
      msgTf = new TextField();
      msgTf.setPrefSize(400, 100);
      idTf = new TextField();
      idTf.setPrefSize(100, 100);
      root.setCenter(textArea);
      
      startBtn = new Button("서버접속");
      startBtn.setPrefSize(250, 100);
      startBtn.setOnAction(t->{
         try {
            //버튼을 누르면 서버 쪽에 server쪽에 socket접속을 시도.
            socket = new Socket("70.12.115.60",2206);
            //만약에 접속을 성공하면 socket객체를 서버로 부터 하나 획득
            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);
            out = new PrintWriter(socket.getOutputStream());
            
            printMsg("채팅 서버 접속 성공.");
            
            Runnable runnable = ()->{
               try {
                  String result;
                  while((result = br.readLine())!= null) {
                     printMsg(result);
                  }

               } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
               
            };
            executorService.execute(runnable);
            
            
         } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      });
      idTf.setOnAction(t->{
         
      });
      msgTf.setOnAction(t->{
         // 입력상자 (textField)에서 enter Key가 입력되면 호출
         String msg = msgTf.getText();
         out.println(msg);
         out.flush();
         msgTf.clear();
      });
      stopBtn = new Button("서버 연결 끊기");
      stopBtn.setPrefSize(250, 100);
      stopBtn.setOnAction(t->{
         try {
            out.println("/@EXIT");
            out.flush();
            out.close();
            br.close();
            isr.close();
            socket.close();
            
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         
      });
      
      FlowPane flowPane = new FlowPane();
      flowPane.setPrefSize(700, 50);
      flowPane.getChildren().add(startBtn);
      flowPane.getChildren().add(msgTf);
      flowPane.getChildren().add(idTf);
      flowPane.getChildren().add(stopBtn);
      root.setBottom(flowPane);   

      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setTitle("클라이언트입니다.");
      primaryStage.show();         
   }
   
   public static void main(String[] args) {
      launch();
   }
}
