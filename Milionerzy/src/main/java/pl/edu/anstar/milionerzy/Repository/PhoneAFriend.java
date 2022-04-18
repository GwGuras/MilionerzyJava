package pl.edu.anstar.milionerzy.Repository;

import java.util.List;
import java.util.Random;

public class PhoneAFriend {
    int lqNumber;
    String lQuestion;
    List<String> lAnswers;
    String lCAnswer;
    public PhoneAFriend(int qNumber,String question,List<String> answers,String cAnswer){
        lqNumber = qNumber;
        lQuestion = question;
        lAnswers = answers;
        lCAnswer = cAnswer;
    }
    public String getPAFAnswer(){
        String pafAnswer;
        Random rand = new Random();
        if(lqNumber<5){
            return lCAnswer;
        }
        else{
            return lAnswers.get(rand.nextInt());
        }

    }
}
