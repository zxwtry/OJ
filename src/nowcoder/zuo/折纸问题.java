package nowcoder.zuo;

public class 折纸问题 {
	public static void main(String[] args) {
		String[] re = foldPaper(4);
		for (int index = 0; index < re.length; index ++) {
			System.out.print(re[index] + "\t");
		}
		System.out.println();
	}
    public static String[] foldPaper(int n) {
    	if (n <= 0) {
    		return null;
    	}
    	final int len = (1 << n) - 1;
        String[] returnString = new String[len];
        boolean isDown = true;
        for (int index = n-1; index >= 0; index --) {
        	final int countAll = (1 << index);
        	final int step = (1 << (n-index));
        	int stepIndex = ((1 << (n-1-index))-1);
        	for (int indexCount = 0; indexCount < countAll; indexCount ++) {
        		if (isDown) {
        			returnString[stepIndex] = "Down";
        		} else {
        			returnString[stepIndex] = "UP";
        		}
        		stepIndex += step;
        		isDown = !isDown;
        	}
        }
        return returnString;
    }
}
