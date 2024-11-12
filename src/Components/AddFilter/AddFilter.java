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
            String[] tokens = line.split(" ");
            String studentId = tokens[0];

            // 인덱스 범위가 유효한지 확인하고 과목 목록 추출
            List<String> studentCourses = tokens.length > 4 ?
                    Arrays.asList(tokens).subList(4, tokens.length) :
                    Collections.emptyList();

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
            String outputLine = studentId + " " + String.join(" ", studentCourses) + "\n";
            if (satisfiesPrerequisites) {
                writer1.write(outputLine); // 만족하는 학생 정보는 Output-1.txt에
            } else {
                writer2.write(outputLine); // 만족하지 않는 학생 정보는 Output-2.txt에
            }
        }

        reader.close();
        writer1.close();
        writer2.close();
        return true;
    }
}