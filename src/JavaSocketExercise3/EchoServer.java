/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSocketExercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastiannielsen
 */
public class EchoServer implements Runnable {
        Socket s;
        BufferedReader in;
        String echo;
        PrintWriter out;
    
    public EchoServer(Socket soc){
        s = soc;
        
    }
    
    @Override
    public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out = new PrintWriter(s.getOutputStream(), true);
                while(true){
                    echo = in.readLine();
                    if(!echo.contains("#") || echo == null){
                        s.close();
                    }
                    String[] cmdAndText = parseCmdAndText(echo);
                    String cmd = cmdAndText[0];
                    switch (cmd.toUpperCase()){
                        case "UPPER": out.println(cmdAndText[1].toUpperCase()); break;
                        case "LOWER": out.println(cmdAndText[1].toLowerCase()); break;
                        case "REVERSE": out.println(reverse(cmdAndText[1])); break;
                        case "TRANSLATE": out.println(translate(cmdAndText[1].toLowerCase()));break;
                        default : s.close(); break;    
                    }
                }   
            } catch (IOException ex) {
                Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private String[] parseCmdAndText(String str){
        
        String[] array = new String[2];
        array[0] = str.substring(0,str.indexOf("#"));
        array[1] = str.substring(str.indexOf("#")+1);
        return array;
    }
    
    private String reverse(String str){
        String reverseText = "";
        for(int i = str.length()-1; i >= 0; i--){
            reverseText += str.charAt(i);
        }
        return reverseText;
    }
    
    private String translate(String str){
        Map<String, String> words = new HashMap<>();
        words.put("hund", "dog");
        words.put("kat", "cat");
        
        if(words.containsKey(str)){
            return words.get(str);
        } else return "#NOT_FOUND";
       
    }
    
    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 4321;
        if(args.length == 2){
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        while(true){
            EchoServer e = new EchoServer(ss.accept());
            Thread t1 = new Thread(e);
            t1.start();
        }
    }


}
