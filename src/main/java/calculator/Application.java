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
            // 1) 시작이 구분자
            char first = input.charAt(0);
            if (first == ',' || first == ':') {
                throw new IllegalArgumentException("잘못된 구분자: 시작이 '" + first + "'");
            }
            // 2) 끝이 구분자
            char last = input.charAt(input.length() - 1);
            if (last == ',' || last == ':') {
                throw new IllegalArgumentException("잘못된 구분자: 끝이 '" + last + "'");
            }
            // 3) 연속 구분자(,, 또는 ::)
            for (int i = 1; i < input.length(); i++) {
                char prev = input.charAt(i - 1);
                char cur = input.charAt(i);
                if ((prev == ',' || prev == ':') && (cur == ',' || cur == ':')) {
                    throw new IllegalArgumentException("잘못된 구분자: 연속 '" + prev + cur + "' (index=" + (i - 1) + ")");
                }
            }

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
            } else {
                throw new IllegalArgumentException("커스텀 구분자 형식 오류");
            }

            String separator = input.substring(2, newlineIndex);
            if(separator.length() != 1){
                throw new IllegalArgumentException("커스텀 구분자 형식 오류: 구분자는 한 글자여야 합니다.");
            }
            String numbers = input.substring(newlineIndex + newlineLength);
            if(numbers.startsWith(separator) || numbers.endsWith(separator)){
                throw new IllegalArgumentException("잘못된 구분자 : 시작/끝에 구분자가 올 수 없습니다.");
            }
            if(numbers.contains(separator + separator)) {
                throw new IllegalArgumentException("잘못된 구분자 : 연속 구분자를 사용할 수 없습니다.");
            }

            numArray = numbers.split(separator);
        }

        // 숫자 합산
        int sum = 0;
        for(String numberString : numArray){
            if(!isAllNumber(numberString)){
                throw new IllegalArgumentException("숫자가 아닌 값 포함: " + numberString);
            }

            int value = Integer.parseInt(numberString);
            if(value <= 0) {
                throw new IllegalArgumentException("0 또는 음수는 허용되지 않는다." + numberString);
            }
            sum += value;
        }
        return sum;
    }

    private static boolean isAllNumber(String n){
        if(n.isEmpty()) return false;
        for(int i = 0; i < n.length(); i++){
            if(!Character.isDigit(n.charAt(i))) return false;
        }
        return true;
    }
}
