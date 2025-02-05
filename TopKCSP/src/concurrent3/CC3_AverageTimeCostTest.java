package concurrent3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import util.Pattern;
import util.Results;

/**
 * author: Hao 
 * date:2015��3��19��
 * time:����2:57:16
 * purpose: The enter class of Top-K Contrast Sequential Pattern mining
 */
public class CC3_AverageTimeCostTest {
	public static void main(String[] args) {
		final int N = 20;
		
		int minGap = 0;
		int maxGap = 2;
		int K = 10;
		final boolean turn = false;
		final boolean a =  true;
		final boolean baseline = false;
		
		final int threadNumber = 1;
		
		String posPath = "./data/ADLs/Activity/A.txt";
		String negPath = "./data/ADLs/Activity/B.txt";
		redirect("./results/att/ADLs/Activity/cc3/ATT "+N+" ", minGap, maxGap, K, threadNumber, posPath, negPath,turn,a,baseline,"");
		
//		String posPath = "./data/ADLs/Location/A_s.txt";
//		String negPath = "./data/ADLs/Location/B_s.txt";
//		redirect("./results/att/ADLs/Location/cc3/ATT "+N+" ", minGap, maxGap, K, threadNumber, posPath, negPath,turn,a,baseline,"");
		
//		String posPath = "./data/genes/eColi/eColi_A.txt";
//		String negPath = "./data/genes/eColi/eColi_B.txt";
//		redirect("./results/att/genes/eColi/cc3/ATT "+N+" ", minGap, maxGap, K, threadNumber, posPath, negPath,turn,a,baseline,"");
		
//		String posPath = "./data/bible/old v3.txt";
//		String negPath = "./data/bible/new v3.txt";
//		redirect("./results/att/bible/bible/cc3/ATT "+N+" ", minGap, maxGap, K, threadNumber, posPath, negPath,turn,a,baseline,"");

//		String X = " x25";
//		String posPath = "./data/ADLs/ActivityX/A X25.txt";
//		String negPath = "./data/ADLs/ActivityX/B X25.txt";
//		redirect("./results/att/ADLs/ActivityX/cc3/ATT "+N+" ", minGap, maxGap, K, threadNumber, posPath, negPath,turn,a,baseline, X);
		
//		String X = " x25";
//		String posPath = "./data/ADLs/LocationX/A_s X25.txt";
//		String negPath = "./data/ADLs/LocationX/B_s X25.txt";
//		redirect("./results/att/ADLs/LocationX/cc3/ATT "+N+" ", minGap, maxGap, K, threadNumber, posPath, negPath,turn,a,baseline, X);
		
//		String X = " x5";
//		String posPath = "./data/genes/eColiX/eColi_A X5.txt";
//		String negPath = "./data/genes/eColiX/eColi_B X5.txt";
//		redirect("./results/att/genes/eColiX/cc3/ATT "+N+" ", minGap, maxGap, K, threadNumber, posPath, negPath,turn,a,baseline,X);
		
//		String X = " x25";
//		String posPath = "./data/bible/bibleX/old X25.txt";
//		String negPath = "./data/bible/bibleX/new X25.txt";
//		redirect("./results/att/bible/bibleX/cc3/ATT "+N+" ", minGap, maxGap, K, threadNumber, posPath, negPath,turn,a,baseline,X);
		
//		String posPath = "./data/test/s";
//		String negPath = "./data/test/t";
		
//		String posPath = "./data/genes/test/a";
//		String negPath = "./data/genes/test/b";
		
//		String posPath = "./data/ADLs/Location/B_s.txt";
//		String negPath = "./data/ADLs/Location/A_s.txt";
		
//		String posPath = "./data/genes/Cbi/Cbi_A.txt";
//		String negPath = "./data/genes/Cbi/Cbi_B.txt";
//		String posPath = "./data/genes/eColi/eColi_A.txt";
//		String negPath = "./data/genes/eColi/eColi_B.txt";
//		String posPath = "./data/genes/tatCD/tatCD_A.txt";
//		String negPath = "./data/genes/tatCD/tatCD_B.txt";
		
		printParameters(minGap, maxGap, K, posPath, negPath, a, baseline);
		
		long start = System.currentTimeMillis();
		for(int i = 0 ;i < N ; i ++){
			TopKCSP topKCSP = new TopKCSP(threadNumber, minGap, maxGap, K, posPath, negPath, a, baseline);
		}
		long end = System.currentTimeMillis();
		double att = (double)(end - start)/N;
		System.out.println("ATT: "+ att/1000+" s");
//		printPattern(results);
		
		
	}

	private static void printPattern(Results results) {
		double min = results.getpList().get(0).cRatio;
		double max = results.getpList().get(results.getpList().size()-1).cRatio;
		double sum = 0;
		for(Pattern p: results.getpList()){
			sum += p.cRatio;
			System.out.println(p.toString());
		}
		double avg = sum/results.getpList().size();
		System.out.println("min: "+min);
		System.out.println("max: "+max);
		System.out.println("avg: "+avg);
	}
	private static void redirect(String path, int minGap, int maxGap, int K, int threadNumber, String posPath, String negPath, boolean turn, boolean a, boolean baseline, String x) {
		
		try {
			String pathname = "";
			pathname = path+"Concurrent2.3 TopK CSP ["+minGap+","+maxGap+"] "+K+" "+ threadNumber +" "+turn+" "+x+" .txt";
			
			PrintStream re = new PrintStream(new File(pathname));
			System.setOut(re);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void printParameters(int minGap, int maxGap, int K, String posPath, String negPath, boolean a, boolean baseline) {
		System.out.println("Method: Concurrent TopK CSP");
		System.out.println("Parameters: ");
		System.out.println(" gap: [" +minGap+", "+maxGap+"]");
		System.out.println(" K: "+K);
		System.out.println(" pos: "+posPath);
		System.out.println(" neg: "+negPath);
		System.out.println("run ***************************");
	}
}
