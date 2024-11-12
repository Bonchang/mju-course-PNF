package Components.AddFilter;

import Framework.CommonFilterImpl;
import Components.Middle.MiddleFilter;
import java.io.*;
import java.util.*;

public class AddFilter extends CommonFilterImpl {
    private Map<String, List<String>> coursePrerequisites; // 선수 과목 정보

    public AddFilter(MiddleFilter middleFilter) {
        // MiddleFilter에서 선수 과목 정보를 가져옴
        this.coursePrerequisites = middleFilter.getCoursePrerequisites();
    }

    @Override
    public boolean specificComputationForFilter() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("Output-1.txt"));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("Output-2.txt"));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue; // 빈 줄인 경우 건너뛰기

            String[] tokens = line.split(" ");
            String studentId = tokens[0];
            String studentLastName = tokens[1];
            String studentFirstName = tokens[2];
            String studentMajor = tokens[3];

            // 유효한 과목 목록만 필터링하여 저장
            List<String> studentCourses = new ArrayList<>();
            for (int i = 4; i < tokens.length; i++) {
                if (!tokens[i].isBlank()) {
                    studentCourses.add(tokens[i]);
                }
            }

            boolean satisfiesPrerequisites = true;

            // 학생의 각 과목에 대해 선수 과목 조건 확인
            for (String course : studentCourses) {
                if (coursePrerequisites.containsKey(course)) {
                    List<String> prerequisites = coursePrerequisites.get(course);
                    if (!studentCourses.containsAll(prerequisites)) { // 선수 과목이 모두 수강되었는지 확인
                        satisfiesPrerequisites = false;
                        break;
                    }
                }
            }

            // 조건에 따라 출력 파일 선택
            StringBuilder outputLine = new StringBuilder();
            outputLine.append(studentId).append(" ")
                    .append(studentLastName).append(" ")
                    .append(studentFirstName).append(" ")
                    .append(studentMajor);
            if (!studentCourses.isEmpty()) {
                outputLine.append(" ").append(String.join(" ", studentCourses));
            }

            if (satisfiesPrerequisites) {
                writer1.write(outputLine.toString());
                writer1.newLine();
            } else {
                writer2.write(outputLine.toString());
                writer2.newLine();
            }
        }

        reader.close();
        writer1.close();
        writer2.close();
        return true;
    }
}