package ua.nure.gunko.practice1;

public class Part6 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] simpleArray = new int[n];
        int nextNumber = 2;
        int counter = 0;
        boolean isSimple;
        while (counter < n) {
            int i = 0;
            isSimple = true;
            while (isSimple && i < counter) {
                if (nextNumber % simpleArray[i] == 0) {
                    isSimple = false;
                }
                i++;
            }
            if (isSimple) {
                simpleArray[counter] = nextNumber;
                counter++;
                nextNumber++;
            } else {
                nextNumber++;
            }
        }
		
		  for(int i=0;i<n;i++) {
			  if(i==n-1) {
				 System.out.print(simpleArray[i]);
			  } else {
				  System.out.print(simpleArray[i]);
				  System.out.print(' ');
			  }
			  
		  }
		 
        
    }
}
