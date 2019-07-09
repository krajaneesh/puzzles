import java.util.List;

class Result {

    /*
     * Complete the 'optimalPoint' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY magic
     *  2. INTEGER_ARRAY dist
     */

    public static int optimalPoint(List<Integer> magic, List<Integer> dist) {
    	
    	for(int i=0; i<magic.size(); i++ ) {
    		if(isCompleteRoutePossible(i,magic, dist) ) {
    			return i; // the least magic index that Alladin can launch onto and complete his route    		
    		}
    	}
    	return -1; // Aladdin is not able to complete his route starting at any of the magic indices  
    }
    
    private static boolean isCompleteRoutePossible(int magicIndex, List<Integer> magic, List<Integer> dist) {
    
    	int startingMagicIndex = magicIndex;
    	int magicBalance = magic.get(magicIndex);
    	
    	do {
    		if(magicBalance >= dist.get(magicIndex)) { // has enough magic for next hop
    			magicBalance = magicBalance - dist.get(magicIndex) + magic.get(getNextIndex(magicIndex, magic.size()-1));
    			magicIndex = getNextIndex(magicIndex, magic.size()-1);
    		}else {
    			return false;
    		}
    	}while(startingMagicIndex != getNextIndex(magicIndex, magic.size()-1)); // full circle is not complete
    	 
    	return true; // full circle completed at this point
    }
    
    /*
     * Returns the next index for a circular array
     * */
    private static int getNextIndex(int currentIndex, int maxIndex) {
    	if(currentIndex >= maxIndex) {
    		return getNextIndex(currentIndex-maxIndex, maxIndex);
    	}else {
    		return currentIndex+1;
    	}
    }

}