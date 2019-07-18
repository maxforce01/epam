package ua.nure.gunko.practice6.part1;

public class Word implements Comparable<Word>{

	  private String content;
	  private int frequency;
	    
	  Word() {}
	  
	  Word (String wrd, int q) {
	    content = wrd;
	    frequency = q;
	  }
	  
	  public String getContent( ) {
	    return content;
	  }
	  
	  public int getFrequency( ) {
	    return frequency;
	  }
	  
	  public int setFrequency() {
	    return frequency++;
	  }

	  public int compareTo(Word a) {
	    int i;
	    if (this.frequency < a.frequency) {
	      i = -1;
	    } else if (this.frequency > a.frequency) {
	      i = 1;
	    } else   {
	      i = a.content.compareTo(this.content);
	    }
	    return i;
	  }
	  
	  public boolean equals(Object obj) {
	    return this == obj ? true : false;
	  }
	  
	  @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + frequency;
	        return result;
	    }
	  
	}
