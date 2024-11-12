package Components.AddFilter;

import Framework.CommonFilterImpl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddFilter extends CommonFilterImpl {
    @Override
    public boolean specificComputationForFilter() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");

            // 조건: 전공이 CS가 아닌 경우
            if (tokens.length >= 4 && !tokens[3].equals("CS")) {
                List<String> courseList = new ArrayList<>(Arrays.asList(tokens).subList(4, tokens.length));

                // 17651과 17652 과목 ID 제거
                courseList.remove("17651");
                courseList.remove("17652");

                StringBuilder updatedLine = new StringBuilder();
                for (int i = 0; i < 4; i++) { // 학번, 이름, 성, 전공까지 복사
                    updatedLine.append(tokens[i]).append(" ");
                }
                for (String course : courseList) { // 수정된 과목 리스트 추가
                    updatedLine.append(course).append(" ");
                }
                System.out.println("[AddFilter] Modified line: " + updatedLine.toString().trim()); // 디버깅용 콘솔 출력
                writer.write(updatedLine.toString().trim());
                writer.newLine();
            }
        }

        reader.close();
        writer.close();
        return true;
    }
}