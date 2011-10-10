package data_collector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author adam
 */
public class NumberMatch {
	/*
	 * 260 02 = Era/T-mobile 260 03 = Orange 260 01 = Plus 260 06 = P4 -play
	 */

	String operators = "(260 02|260 03|260 01|260 06)";

	public NumberMatch() {

	}

	public int matchNumber(String number) throws IOException {
		int result = 0;
		try {
			URL tmobileMatch = new URL(
					"http://download.t-mobile.pl/updir/updir.cgi?msisdn="
							+ number);
			System.out.println(tmobileMatch);
			URLConnection numberMatcherCon = tmobileMatch.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					numberMatcherCon.getInputStream()));
			String inputLine;
			Pattern operatorPattern = Pattern.compile(operators);
			while ((inputLine = in.readLine()) != null) {
				// System.out.println(inputLine);
				Matcher operatorMatcher = operatorPattern.matcher(inputLine);
				while (operatorMatcher.find()) {
                                    //System.out.println(operatorMatcher.find());
					int startIndex = operatorMatcher.start();
					int endIndex = operatorMatcher.end();
                                     //   System.out.println(startIndex + " " +  endIndex);
					String currentMatch = inputLine.substring(startIndex,endIndex);
					//System.out.println(currentMatch);
					String liczba = currentMatch.substring(currentMatch.length()-1, currentMatch.length()).toString();
					//System.out.print(liczba);
					result = new Integer(liczba);
				}

			}
			in.close();
		} catch (MalformedURLException ex) {
			Logger.getLogger(Tmobile_sender.class.getName()).log(Level.SEVERE,
					null, ex);
			result = -1;
		}
		//System.out.println(result);
		return result;
	}

}
