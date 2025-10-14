package calculator;
import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        int result = add(input);
        System.out.println("결과 : " + result);
    }

    public static int add(String input) {
        if(input == null || input.isEmpty()) {
            return 0;
        }

        String[] numArray = null;

        if(!input.startsWith("//")){
            String[] commaSplit = input.split(",");
            List<String> numStr = new ArrayList<>();

            for(String num : commaSplit){
                String[] colonSplit = num.split(":");
                for(String t : colonSplit){
                    numStr.add(t);
                }
            }
            numArray = numStr.toArray(new String[0]);
        }

        // 숫자 합산
        int sum = 0;
        for(String n : numArray){
            int number = Integer.parseInt(n);
            sum += number;
        }
        return sum;
    }
}
