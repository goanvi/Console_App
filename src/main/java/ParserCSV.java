import java.util.Arrays;
import java.util.Collection;

public class ParserCSV {
    public static <T> String toCSV(Collection<T> collection) {
        StringBuffer stringBuffer = new StringBuffer("");
        Object[] collArray = collection.toArray();
        String[] header = makeHeader(collArray[0]);
        stringBuffer.append(Arrays.toString(header).replace("[","").replace("]",""));
        stringBuffer.append("\n");
        for (Object obj : collArray) {
            stringBuffer.append(Arrays.toString(makeData(obj)).replace("[","").replace("]",""));
            stringBuffer.append("\n");
        }
        return String.valueOf(stringBuffer);
    }

//    public fromCSV (String string){
//
//    }

    private static String[] makeHeader(Object object) {
        String[] recordHd = object.toString().split(",");
        String[] header = new String[recordHd.length];
        for (int i = 0; i < recordHd.length; i++) {
            String[] sword = recordHd[i].split("=");
            header[i] = sword[0];
        }
        return header;
    }

    private static String[] makeData(Object object) {
        String[] recordDt = object.toString().split(",");
        String[] data = new String[recordDt.length];
        for (int i = 0; i < recordDt.length; i++) {
            String[] sword = recordDt[i].split("=");
            data[i] = sword[sword.length -1];
        }
        return data;
    }


}
