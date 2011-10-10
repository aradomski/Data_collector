/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_collector;

/**
 *
 * @author adam
 */
public class TMobileReportCreator {
    private Integer[] result =new Integer[4];
    private  String report;

    public TMobileReportCreator(Integer[] result) {
        this.result = result;
        switch (result[0]) {
            case 0: report = "Wiadomość wysłano poprawnie \n";
                break;
            case 1: report = "Awaria systemu \n";
                break;
            case 2: report = "Błąd logowania \n";
                break;
            case 3: report = "Brak dostępu \n";
                break;    
            case 5: report = "Błąd zapytania do bazy \n";
                break;  
            case 7: report = "Wyczerpany limit wiadomości \n";
                break;    
            case 8: report = "Błędny adres odbiorcy \n";
                break;    
            case 9: report = "Wiadomość zbyt długa \n";
                break;  
            case 10: report = "Brak wymaganej ilości żetonów \n";
                break; 
            default: report = "Nieznany błąd. Kod:"+ result[0] + "\n";
        }
        if(result[1]!=0 && result[2]==0 && result[3]==0){
            report = report + "Pozostało: " + result[1] + " wiadomości do wysłania z darmowej skrzynki \n";
        }if(result[0]==0 && result[1]==0){
            report = report + "Pozostało: " + result[2] + " żetonów. \n Do wysłania tej wiadmości zużyto: " + result[3] + " żetonów \n";
        }
        
    }
    
    public String toString(){
        return report;
    }
}
