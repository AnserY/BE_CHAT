/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
/**
 *
 * @author anser
 */
public class PresenteServerControler {
    
    public HttpClient client;
    public PresenteServerControler(){
        this.client = HttpClientBuilder.create().build();
    }
    
    public void subscribe(String pseudo, String ipAddress) throws IOException{
        HttpPost request = new HttpPost("http://192.168.1.61:8080/jiji/PresenceServer?param1="+pseudo+"&param2="+ipAddress);
        HttpResponse response = client.execute(request);
    }
   
    public void deco(String pseudo, String ipAddress) throws IOException{
        HttpGet request = new HttpGet("http://192.168.1.61:8080/jiji/PresenceServer?param1="+pseudo+"&param2="+ipAddress);
        HttpResponse response = client.execute(request);
    }


}