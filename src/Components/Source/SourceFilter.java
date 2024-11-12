package Components.Source;

import Framework.CommonFilterImpl;
import java.io.*;

public class SourceFilter extends CommonFilterImpl {
    private String filename;

    public SourceFilter(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean specificComputationForFilter() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue; // 빈 줄인 경우 건너뛰기

            System.out.println("[SourceFilter] Read line: " + line); // 디버깅용 출력

            for (char c : line.toCharArray()) {
                out.write(c); // 각 문자를 다음 필터로 전달
            }
            out.write('\n'); // 줄바꿈 추가하여 한 줄로 전달
        }
        reader.close();
        return true;
    }
}