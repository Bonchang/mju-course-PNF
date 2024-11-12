package Components.AddFilter;

import Framework.CommonFilterImpl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AddFilter extends CommonFilterImpl {
    @Override
    public boolean specificComputationForFilter() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            if (tokens.length >= 4 && tokens[3].equals("EE")) { // EE 학생인지 확인
                boolean has23456 = false;
                for (int i = 4; i < tokens.length; i++) { // 과목 ID 체크
                    if (tokens[i].equals("23456")) {
                        has23456 = true;
                        break;
                    }
                }
                if (!has23456) { // 과목 ID 추가
                    StringBuilder updatedLine = new StringBuilder(line);
                    updatedLine.append(" 23456");
                    writer.write(updatedLine.toString());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            } else {
                writer.write(line);
                writer.newLine();
            }
        }
        reader.close();
        writer.close();
        return true;
    }
}