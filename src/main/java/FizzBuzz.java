import org.junit.Test;

public class FizzBuzz {

    public static String GetFizzBuzz(int number){
        String result="";
        result=String.valueOf(number);
        if(number%3==0){
            result="Fizz";
        }
        if(number%5==0){
            result="Buzz";
        }
        if(number%15==0){
            result="FizzBuzz";
        }
        return result;
    }

    public static void main(String[] args) {
      for(int i=1;i<101;i++){
          System.out.println(GetFizzBuzz(i));
      }

    }
}

