package Components.Middle;

import Framework.CommonFilterImpl;
import java.io.*;
import java.util.*;

public class MiddleFilter extends CommonFilterImpl {
    private Map<String, List<String>> coursePrerequisites = new HashMap<>();

    @Override
    public boolean specificComputationForFilter() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue; // 빈 줄인 경우 건너뛰기

            String[] tokens = line.split(" ");
            String courseId = tokens[0];
            if (tokens.length > 3) {
                List<String> prerequisites = Arrays.asList(tokens).subList(3, tokens.length);
                coursePrerequisites.put(courseId, prerequisites);
            } else {
                coursePrerequisites.put(courseId, new ArrayList<>());
            }
        }
        reader.close();
        return true;
    }

    public Map<String, List<String>> getCoursePrerequisites() {
        return coursePrerequisites;
    }
}