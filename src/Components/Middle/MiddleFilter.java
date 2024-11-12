package Components.Middle;

import Framework.CommonFilterImpl;
import java.io.*;
import java.util.*;

public class MiddleFilter extends CommonFilterImpl {
    // 선수 과목 정보를 저장하는 맵 <과목 ID, 선수 과목 목록>
    private Map<String, List<String>> coursePrerequisites = new HashMap<>();

    @Override
    public boolean specificComputationForFilter() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            String courseId = tokens[0];
            if (tokens.length > 3) { // 선수 과목이 있는 경우
                List<String> prerequisites = Arrays.asList(tokens).subList(3, tokens.length);
                coursePrerequisites.put(courseId, prerequisites);
            } else {
                coursePrerequisites.put(courseId, new ArrayList<>()); // 선수 과목이 없는 경우 빈 리스트로 저장
            }
        }

        reader.close();
        return true;
    }

    // AddFilter에서 사용할 수 있도록 선수 과목 정보를 반환하는 메서드
    public Map<String, List<String>> getCoursePrerequisites() {
        return coursePrerequisites;
    }
}