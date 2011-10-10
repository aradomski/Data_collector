/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data_collector;

/**
 *
 * @author adam
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tmobile_sender {
	private static final int ERROR = -1;
	private String login;
	private char[] password;
	private String reciever_number;
	public boolean sponsored;
	public String message;
	public boolean mms;
	public String sucess;
	public String faliure;
        private int[] response =new int[4];
	/**
	 * @param login
	 * @param password
	 * @param reciever_number
	 * @param sponsored
	 * @param message
	 * @param mms
	 * @param sucess
	 * @param faliure
	 */
	public  Tmobile_sender(String login, char[] password,
			String reciever_number, boolean sponsored, String message,
			boolean mms, String sucess, String faliure) throws IOException {
		super();
		this.login = login;
		this.password = password;
		this.reciever_number = reciever_number;
		this.sponsored = sponsored;
		this.message = message;
		this.mms = mms;
		this.sucess = sucess;
		this.faliure = faliure;              
		// System.out.println(password);

		String params = "message=" + this.message + "&number="
				+ this.reciever_number + "&password=";
		for (int i = 0; i < password.length; i++) {
			params = params + password[i];
		}
		params = params + "&login=" + this.login + "&failure=" + this.faliure
				+ "&success=" + this.sucess + "&mms=" + this.mms;
		// System.out.println(params);
                System.out.println("sponsored ="+sponsored);
		if (sponsored) {
                    System.out.println("Bramka sponsorowana");
                        try {
				URL sponsoredURL = new URL(
						"http://www.t-mobile.pl/msg/api/do/tinker/sponsored?"
								+ params);
				System.out.println(sponsoredURL);
				URLConnection urlConnection = sponsoredURL.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						urlConnection.getInputStream()));
				 String inputLine;
				 while ((inputLine = in.readLine()) != null){                            
				 System.out.println(inputLine);
                                 }
				in.close();

			} catch (MalformedURLException ex) {
				Logger.getLogger(Tmobile_sender.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		} else {
                    System.out.println("Bramka płatna");
			try {

				URL omnixURL = new URL(
						"http://www.t-mobile.pl/msg/api/do/tinker/omnix?"
								+ params);
				System.out.println(omnixURL);
				URLConnection urlConnection = omnixURL.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						urlConnection.getInputStream()));
				 String inputLine;
				 while ((inputLine = in.readLine()) != null){
                                 System.out.println(inputLine);                               
                                 }
				
				in.close();

			} catch (MalformedURLException ex) {
				Logger.getLogger(Tmobile_sender.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}             
	}
}
