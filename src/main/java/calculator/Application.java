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
        } else {
            int idxEsc = input.indexOf("\\n"); // 리터럴 '\' + 'n'
            int idxLF = input.indexOf('\n');   // 실제 개행 문자

            int newlineIndex = 0;
            int newlineLength = 0;

            if (idxEsc >= 0) {
                newlineIndex = idxEsc;
                newlineLength = 2;
            } else if(idxLF >= 0) {
                newlineIndex = idxLF;
                newlineLength = 1;
            }

            String separator = input.substring(2, newlineIndex);
            String numbers = input.substring(newlineIndex + newlineLength);

            numArray = numbers.split(separator);
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
