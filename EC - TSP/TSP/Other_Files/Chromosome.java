import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Chromosome {

    /**
     * The list of cities, which are the genes of this chromosome.
     */
    protected int[] cityList;

    /**
     * The cost of following the cityList order of this chromosome.
     */
    protected double cost;

    /**
     * @param cities The order that this chromosome would visit the cities.
     */
    Chromosome(City[] cities) {
        Random generator = new Random();
        cityList = new int[cities.length];
        //cities are visited based on the order of an integer representation [o,n] of each of the n cities.
        for (int x = 0; x < cities.length; x++) {
            cityList[x] = x;
        }

        //shuffle the order so we have a random initial order
        for (int y = 0; y < cityList.length; y++) {
            int temp = cityList[y];
            int randomNum = generator.nextInt(cityList.length);
            cityList[y] = cityList[randomNum];
            cityList[randomNum] = temp;
        }

        calculateCost(cities);
    }

    /**
     * Calculate the cost of the specified list of cities.
     *
     * @param cities A list of cities.
     */
    void calculateCost(City[] cities) {
        cost = 0;
        for (int i = 0; i < cityList.length - 1; i++) {
            double dist = cities[cityList[i]].proximity(cities[cityList[i + 1]]);
            cost += dist;
        }

        cost += cities[cityList[0]].proximity(cities[cityList[cityList.length - 1]]); //Adding return home
    }

    /**
     * Get the cost for this chromosome. This is the amount of distance that
     * must be traveled.
     */
    double getCost() {
        return cost;
    }

    /**
     * @param i The city you want.
     * @return The ith city.
     */
    int getCity(int i) {
        return cityList[i];
    }

    /**
     * Set the order of cities that this chromosome would visit.
     *
     * @param list A list of cities.
     */
    void setCities(int[] list) {
        for (int i = 0; i < cityList.length; i++) {
            cityList[i] = list[i];
        }
    }

    /**
     * Set the index'th city in the city list.
     *
     * @param index The city index to change
     * @param value The city number to place into the index.
     */
    void setCity(int index, int value) {
        cityList[index] = value;
    }
    
    /**
     * Sort the chromosomes by their cost.
     *
     * @param chromosomes An array of chromosomes to sort.
     * @param num         How much of the chromosome list to sort.
     */
    public static void sortChromosomes(Chromosome chromosomes[], int num) {
        Chromosome ctemp;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < num - 1; i++) {
                if (chromosomes[i].getCost() > chromosomes[i + 1].getCost()) {
                    ctemp = chromosomes[i];
                    chromosomes[i] = chromosomes[i + 1];
                    chromosomes[i + 1] = ctemp;
                    swapped = true;
                }
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Mutates a chromosome by randomly selecting a segment, and then reversing it
     */
    public void inversionMutation(){
		Random rng = new Random();
    	int index1 = rng.nextInt(cityList.length);
    	int index2 = rng.nextInt(cityList.length);
    	int segmentStart = Math.min(index1, index2);
    	int segmentEnd = Math.max(index1, index2);
    	int segmentLength = segmentEnd - segmentStart;
    	
    	for(int i = segmentStart; i <= (int)(segmentLength/2)+segmentStart; i++){
    		int temp = cityList[i];
    		cityList[i] = cityList[segmentStart + segmentEnd - i];
    		cityList[segmentStart + segmentEnd - i] = temp;
    	}
    	
    }
    
    /**
     * Mutates a chromosome by randomly switching two genes
     */ 
    public void transpositionMutation(){
    	Random rng = new Random();
    	int index1 = rng.nextInt(cityList.length);
    	int index2 = rng.nextInt(cityList.length);
    	
    	int temp = cityList[index1];
    	cityList[index1] = cityList[index2];
    	cityList[index2] = temp;
    }
    
    /**
     * Mutates a chromosome by shifting a randomly chosen segment to a third point
     */
    public void shiftingMutation(){
    	Random rng = new Random();
    	int index1 = rng.nextInt(cityList.length);
    	int index2 = rng.nextInt(cityList.length);
    	int index3 = rng.nextInt(cityList.length);    	
    	int[] indices = {index1, index2, index3};
    	Arrays.sort(indices);

    	int segmentStart = indices[0];
    	int segmentEnd = indices[1];
    	index3 = indices[2];
    	int segmentLength = segmentEnd - segmentStart;

    	if (segmentLength == index3 - segmentEnd){
    		for(int i = 0; i < segmentLength; i++){
    			int temp = cityList[segmentStart + i];
    			cityList[segmentStart + i] = cityList[segmentEnd + i];
    			cityList[segmentEnd + i] = temp;
    		}
    	}
    	else {
    		int[] segment = new int[segmentLength];
    		System.arraycopy(cityList, segmentStart, segment, 0, segmentLength);		
	    	for (int i = 0; i < index3 - segmentEnd; i++){
	    		cityList[segmentStart + i] = cityList[segmentStart + i + segmentLength];
	    	}
	    	for (int i = 0; i < segment.length; i++){
	    		cityList[index3 - segmentLength + i] = segment[i];
	    	}
    	}
    }
    
    /**
     * Mutates a chromosome by randomly selecting a gene, and inserting it into a random new position
     */
    public void translocationMutation(){
		Random rng = new Random();
    	int index1 = rng.nextInt(cityList.length);
    	int index2 = rng.nextInt(cityList.length);
    	
    	if (index1 < index2){
    		for(int i = index1; i < index2 - 1; i++){
    			int temp = cityList[i];
    			cityList[i] = cityList[i + 1];
    			cityList[i + 1] = temp;
    		}
    	}
    	else{
    		for(int i = index1; i > index2; i--){
    			int temp = cityList[i];
    			cityList[i] = cityList[i - 1];
    			cityList[i - 1] = temp;
    		}
    	}  
    }
    
    
    
    
}
