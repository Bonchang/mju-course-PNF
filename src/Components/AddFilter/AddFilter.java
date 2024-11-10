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

            if (tokens.length >= 4 && tokens[3].equals("CS")) { // CS 학생인지 확인
                boolean has12345 = false;
                boolean has23456 = false;

                // 과목 ID 체크
                for (int i = 4; i < tokens.length; i++) {
                    if (tokens[i].equals("12345")) {
                        has12345 = true;
                    }
                    if (tokens[i].equals("23456")) {
                        has23456 = true;
                    }
                }

                // 과목 ID 추가
                if (!has12345 || !has23456) {
                    StringBuilder updatedLine = new StringBuilder(line);
                    if (!has12345) {
                        updatedLine.append(" 12345");
                    }
                    if (!has23456) {
                        updatedLine.append(" 23456");
                    }
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
