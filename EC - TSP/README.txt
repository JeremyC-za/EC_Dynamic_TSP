Jeremy Coupland (CPLJER001)
EC Assignment
26/05/2016

My Evolutionary Algorithm
My algorithm successfully minimises the dynamic travelling salesman proble, to a point. To do this, I implemented four different mutation methods (inversion, shifting, transposition, translocation) and two different parent selection methods (elitist and tournament). For my testing, I have identified some parameters that produce best results for my algorithm. My results are all averaged over 100 runs, of 100 generations each. The parent selection methods can be altered in the evolve() method in TSP.java, and the mutation methods can be altered in the selectOffspring() method, also in TSP.java.



Best Results
The best results I have recorded is an average cost of 4212. This was achieved by using elitist selection to select the most fit parent from each generation. The parent then produces 100 children, which replace the parent in the new generation. It was found that using Inversion mutation for the first 94 generations, and then translocation mutation for the remaining generations produced optimal results, as this balances the ratio of exploration vs exploitation. Individual test results can be found in the spreadsheet Results_Different_Methods.xlsx attached. 



Statistical Tests (with results from Josh di Bona - DBNJOS001)
The statistical analysis was performed using results that I produced, and Josh di Bona's results. The details of the analysis can be seen in Stat_Analysis.xlsx. The results per dataset are as follows:

My Lowest = 3650
My Average = 4258
My Highest = 4670

Josh's Lowest = 3576
Josh's Average = 4415
Josh's Highest = 4971

The results of each 100 runs of both datasets were inserted into a spreadsheet and found to follow a normal distribution. However, both datasets had unequal variance. As a result of this, a t-Test assuming unequal variances was performed. 
The null hypothesis for this test is that both datasets are equal (or similar). A t Stat value was calculated to be -9.6, and a t Critical two-tail value was calculated to be 1.97. According to the t-Test, if (t Stat < -t Critical two-tail) or (t Stat > t Critical two-tail), then the null hypothesis can be rejected. This is the case, and thus we can conclude that the two datasets (algorithms) are statistically different. 

This difference was unexpected, as we both used elitist parent selection, only selecting the fittest parent, both of us produced 100 children from this parent, and both of us used inversion mutation for the most part. The few generations in which I used translocation mutation did not make such a significant difference. I suspect that the implementation of each of our inversion mutation methods is what is causing this difference. For inversion mutation, a random segment of the geneotype is selected, and inserted into a random location. I have not seen Josh's implementation, so I cannot speak for him, but in my method, three random numbers are generated: the start and end of the segment, and the insertion point. The segment is then inserted into the insertion point index, and the indices before it cascade downwards to fill the gap left from the segment. If the insertion point is anywhere between the segment start and segment end, the mutation method will have no effect, as the segment will be removed and inserted, and cascade back into its original position. In my implementation I avoided this by sorting the numbers before selecting the three indices, but this may not be the case in Josh's algorithm, which may be responsible for the difference in results. 