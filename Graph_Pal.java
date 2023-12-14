import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//1190707 -Lojain Abdalrazaq
//1190324-Arwa-Doha
public class Graph_Pal {
	
	/// represent the number of nodes or cities in the Graph
	int Ver;  
	
	/// Initializing a constant variable that represent the number of cities in the input files
	final static int NumCities = 20;
	final static int MAX = 9999999;
	/// Adjacency list to implement the Graph using it
    LinkedList<Integer>[] adjList;   
    
    /// Maintaining a queue
    Queue<Integer> q;
    
    /// Creating an matrix (2-D array) of type CITY
    static City [][] matrix_c = new City[NumCities][NumCities];
    
 	// ArrayLists will be used in the A* functions 
 	static ArrayList<String> c1 = new ArrayList<String>();

 	static double costA1_final = 0;
    
    
    ///*---------------------------------------<< Graph Constructor Function >>-----------------------------*///
    /// Creating the constructor of the graph
    /// it takes the number of nodes (cities)
    Graph_Pal(int Ver) { 
    	
        this.Ver = Ver; 
        
        /// Array of linked list of size (Ver)
        adjList = new LinkedList[Ver]; 
        
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new LinkedList<Integer>(); 
        }
        q = new LinkedList<Integer>();
    } 
     
    
    ///*---------------------------------------<< Adding Edge Function  >>----------------------------------*///   
    /// This function for adding edge to the adjList
    /// between the cities that are connected directly
    void addEdge(int city1, int city2){ 
        adjList[city1].add(city2);                             
    } 
    
    ///*---------------------------------------<< Reading Cities Function >>----------------------------------*///
    /// This function is for reading the cities names from the input city file
    public static String[] ReadCities(String CityFile) throws Exception {
	  
      /// Crating new file
  	  File inFile = new File(CityFile);
  	  
  	  /// Scanning the input file that contains the names of the nodes 
  	  /// the nodes are the cities
  	  Scanner in = new Scanner(inFile);
  
  	  /// Initializing 1-D  array of type STRING 
  	  /// to store the names of the cities in it 
  	  String [] Cities_Name = new String[NumCities];
  	  
  	  /// counter for the lines
  	  int counter = 0;
  		
  	  /// While loops in all cities in the input file until finish all lines
  	  while (in.hasNextLine()) {
  		      for (int i = 0; i < NumCities; i++) {
  			    String currentLine = in.nextLine(); 
  			    /// Storing the read data in the 1-D array
  			    Cities_Name[i] = currentLine;  
  		      }
  		        /// Increment the counter
  			  counter++;
  			 } 
  	   
  	 in.close();
    /// Returns the 1-D array that contains the names of the nodes (cities)
  	return Cities_Name;	
  	}
    
    ///*---------------------------------------<< Reading Costs Function >>----------------------------------*///
    /// This function read the costs for every city
    /// and store it in 2-D array (matrix) of STRING 
    public static String[][] ReadCost(String filename) throws Exception {
  	  
      /// Creating a matrix of size the number of cities
      /// to store the values of the 3 costs 
      /// The costs are areal, car_cost, walk_cost
  	  String [][] matrix_cost = new String[NumCities][NumCities];
  	  
  	  /// Creating the file of costs
  	  File inFile= new File(filename);
  	  /// Scanning the input cost file
  	  Scanner in_cost = new Scanner(inFile);
 
  	  /// counter for the lines
      int counter = 0;
      
      /// While loops in all costs in the input file until finish all lines
  	  while (in_cost.hasNextLine()) {
  		  
  		  /// Splitting each line to the denameter --> #
  		  String[] currentLine_cost = in_cost.nextLine().trim().split("#"); 
  		  /// Storing the read parts in the 2-D array --> matrix
  		  for (int i = 0; i < currentLine_cost.length; i++) {
  		        matrix_cost[counter][i] = currentLine_cost[i];
  		        // string cost,h1,h1
  		        } 
  		   /// Increment the counter
  		  counter++;
  		  
  		 }
  	  in_cost.close();
    /// Returns the matrix that contains the costs of all cities 
  	return matrix_cost;
  }
   
    ///*---------------------------------------<< Depth First Search Algorithm >>----------------------------------*///
    /// Void function takes the source and the destination as input values
    /// Prints the path from the source node to the destination according to DFS algorithm
    void DFS_Function(int src, int dest) { 
     	
    	/// Initializing 1-D  array of type BOOLEAN
    	/// The size of the array is the number of nodes or cities in the graph
        boolean nodes[] = new boolean[Ver];   
        
        /// Initializing stack of type integer
        Stack<Integer> stack = new Stack<>(); 
        
        /// The first step is adding or pushing the source city into stack
        stack.push(src);  
        
        int a = 0;
        
        /// While loop iterate while the stack not EMPTY
        while(!stack.empty()) { 
        	
            /// storing it the variable in the top of stack in the variable
       	    src = stack.peek(); 
            
            /// removing it from the top of the stack
            stack.pop();                        
            
            if(nodes[src] == false) { 
            	// Adding it to the printed path
                System.out.print(matrix_c[src][src].Source + " "); 
                /// Make it true
                nodes[src] = true; 
            } 
            /// Checking condition
            /// If the n variable equals to the destination
            /// No need to complete the whole graph
            if (dest==src) {
             /// Break from the while loop
        	 break;
            }
            /// For loop iterate through the array of the liked list (the graph)
            for (int i = 0; i < adjList[src].size(); i++) { 
            a = adjList[src].get(i);
            /// If the node is not in the stack
            /// Pushing it into the stack --> the top of the stack
            if (nodes[a]==false){
                stack.push(a);                          
       }
      }        
     } 
    }

    ///*---------------------------------------<< Breadth First Search Algorithm >>--------------------------------*///
    /// Void function takes the source and the destination as input values
    /// Prints the path from the source node to the destination according to BFS algorithm
    void BFS_Function(int src , int dest) {
    	
    	/// Initializing 1-D array of type BOOLEAN
    	/// The size of the array is the number of nodes or cities in the graph
        boolean nodes[] = new boolean[Ver];       
        
        int a = 0;

        /// Putting the source node true 
        nodes[src]=true;    
        
        /// Adding the start node to the queue
        q.add(src);                   

        /// While loop continue while the queue is NOT EMPTY
        while (q.size() != 0){
        	
        	/// Remove the top element of the queue
            src = q.poll();     
            /// Printing the path
            System.out.print(matrix_c[src][src].Source+" ");
            
            /// If the source equals destination 
            /// we break the loop 
            if (src==dest) {
            	break;
            }
            /// For loop iterate through the linked list and push all neighbors (connected cities or nodes) into queue
            for (int i = 0; i < adjList[src].size(); i++)  {
                a = adjList[src].get(i);
                
                // If the node is FALSE
                // it will be added 
                if (nodes[a]== false) {                   
                    nodes[a] = true;
                    q.add(a);
                }
            }  
        }
    }
    
    ///*---------------------------------------<< Printing Graph Function >>------------------------------------*///
    /// This function print the graph of cities
    void printGraph(){
		
    	/// For loop iterate over the graph
    	/// The graph that implemented using the Linked list 
	    for (int i = 0; i < Ver ; i++) { 
	    	/// If the node i in the array is connected to to another nodes
	        if(adjList[i].size()>0) {
	        	/// Printing the nodes
	            System.out.print("Vertex " + i + " is connected to: ");
	            
	            /// For loop for all nodes connected to index i in the 1-D array
	            for (int j = 0; j < adjList[i].size(); j++) {
	                System.out.print(adjList[i].get(j) + " ");
	            }
	            /// Printing new line.
	            System.out.println();
	        }
	    }
	}

    //*---------------------------------------<< A* Algorithm --> Aerial  >>------------------------------------*///
    /// This function for walking distance using Aerial distance as the heuristic (h1).
 	//Use this to find the walking distance from each node to the goal G.
 	public static double a_function(int src, int dest) {

		/// Initializing an array of integers with NumCities size
		int[] Dlength = new int[NumCities];
		
		/// An array of type BOOLEAN to know which nodes are visited and which are not.
		boolean[] nodes_visited = new boolean[NumCities];
		
		/// Putting all indexes in the array as very large value --> MAX 
		Arrays.fill(Dlength,MAX);
		
		/// The distance for the same node is = 0
		Dlength[src] = 0;

		/// Created an array of type double of size NumCities 
		double[] nodes = new double[NumCities];
		
		/// To fill complete priorities array & represents the (MAX) value using .fill FUNCTION
		Arrays.fill(nodes, MAX);

	
		/// getting the aerial distance of the src node 
		nodes[src] = matrix_c[src][dest].h_areal;

		
		/// While true 
		while (true) {
			
			/// The index of the fewest cost path
			int Cost_In = -1;
			double lowest_Cost = MAX;

			/// check the nodes we haven't visited
			for (int i = 0; i < nodes.length; i++) {
				if (nodes[i] < lowest_Cost && !nodes_visited[i]) {
					lowest_Cost = nodes[i];
					Cost_In = i;
				}
			}
			if (Cost_In == -1) {
				/// Means that the node is not found, or not found YET
				return -1;
			}

			/// When the destination is found
			else if (Cost_In == dest) {
				System.out.println("The desired destination found *FINALLY*--> " + matrix_c[Cost_In][Cost_In].Source);
				/// Returns the cost of the path to the destination
				return Dlength[Cost_In];
			}
			
			System.out.println(" "+ matrix_c[Cost_In][Cost_In].Source );
            
			for (int i = 0; i < NumCities; i++) {
				/// This for loop checks the nodes that are connected to it
				/// If it is not equals to 0 (SAME NODE) or is not Connected.
				if (matrix_c[Cost_In][i].car_cost != 0 && matrix_c[Cost_In][i].car_cost != -1 && !nodes_visited[i]) {
					if (Dlength[Cost_In]+ matrix_c[Cost_In][i].car_cost < Dlength[i]) {
                        
						Dlength [i] = Dlength[Cost_In]+ matrix_c[Cost_In][i].car_cost;
                        /// Adding the aireal distance
						nodes[i] = Dlength[i] + matrix_c[i][dest].h_areal;
						costA1_final = Dlength[dest];

					}
				}
			}

			nodes_visited[Cost_In] = true;
			c1.add(matrix_c[Cost_In][Cost_In].Source);
			
			/// For loop to print the visited node until we got the *DESTINATION*
			

		}
 	}
    ///*---------------------------------------<< The main Function >>-----------------------------------------*///
    public static void main(String[] args) throws Exception {
    	
    	
    	/// --------------Reading the cities and the costs  text files-----------------///
    	/// Storing the output of the function in 1-D and 2-D array of matrix
    	String [][] matrix_cost= ReadCost("Text.txt");
		String [] Cities_Name= ReadCities("Cities.txt");
		
		/// --------------Storing the values in the matrix of type City----------------///
		/// This for loop will store the values stores in the matrixes in the matrix of type --> CITY
		for (int i = 0; i<NumCities; i++) {
			   for(int j = 0; j<NumCities; j++) {
				   String s = matrix_cost[i][j];
				   String[] tokens = s.split(",");
				   /// In every iterate, we create an object of type CITY
				   matrix_c[i][j] = new City();
				   matrix_c[i][j].car_cost=Integer.parseInt(tokens[1]); /// The car cost attribute
				   matrix_c[i][j].h_walk=Integer.parseInt(tokens[2]);   /// The heuristic --> Walk attribute
				   matrix_c[i][j].h_areal=Integer.parseInt(tokens[0]);  /// The heuristic --> Aerial attribute
				   matrix_c[i][j].Source=Cities_Name[i]; /// The Source attribute
				   matrix_c[i][j].Destination=Cities_Name[j]; /// The Destination attribute	
	      }
		}
		/// -------------graph implementation using Adj List-------------------------///
		/// The size of the graph will be the number of cities --> nodes
		Graph_Pal g = new Graph_Pal(NumCities);
		for (int i = 0; i<NumCities; i++) {
			   /// For loop iterate over the matrix of city 
		       /// We add edge to the connected nodes or cities
			   /// the connected nodes has a car cost, 
			   for(int j = 0; j<NumCities; j++) {
				   /// if the car cost is not -1 which means it connected
				   /// and if the car cost is not 0, which means not the same node
				   /// we add edge between them
				   if (matrix_c[i][j].car_cost != -1 && matrix_c[i][j].car_cost !=0) {
					   g.addEdge(i,j); 
		            }
		          }
	          }
		
		System.out.println("*************************************************************************************");
		System.out.println("*                   Welcome to PALESTINIAN Rout Navigation System                   *");
		System.out.println("*************************************************************************************");
		System.out.println("* Dear User :)                                                                      *");
		System.out.println("* Please Select one of the Palestinian Cities as a source city:                     *");
		System.out.print("*");
		for (int i = 0; i<NumCities; i++) {
			if (i==10) {
				System.out.print("\n");
				System.out.print("*");
			}
			System.out.print(" "+Cities_Name[i]+" ");
		}
		System.out.println();
		System.out.println("*************************************************************************************");
		System.out.println();
		
		
		/// -------------------------reading the source and destination from the user--------------------------------///
		int in_src=0;
        int in_dest=0;
        int Flag_src=0;
        int Flag_dest=0;
        int Flag_algo=0;
		
	    /// Reading the first city which is the source city
		System.out.println("So please enter the << SOURCE >> city: ");
        Scanner input_city1= new Scanner(System.in);
        String start= input_city1.nextLine();
        
        /// For loop to check if the input city is valid or not
        for(int i=0; i< NumCities ;i++){
        	if(matrix_c[i][i].Source.equalsIgnoreCase(start)) {
        		/// if it found, 
        		/// storing the index in the in_src variable
        		in_src=i;
        		/// putting the flag equals to 1 --> which means that its VALID
        		Flag_src=1;	
        	}
        }
        
        /// While loop will continue until the user write a VALID city name
        while(Flag_src==0){
        	System.out.println("Sorry, You have entered wrong SOURCE :( , Please Try again!!");
        	System.out.println("So please enter the << SOURCE >> city: ");
            input_city1= new Scanner(System.in);
            start= input_city1.nextLine();
        	for(int i=0; i< NumCities ;i++){
            	if(matrix_c[i][i].Source.equalsIgnoreCase(start)) {
            		in_src=i;
            		/// putting the flag equals to 1 --> which means that its VALID
            		Flag_src=1;
            		break;
            	}	
            }	
        }
        
        /// Reading the second city which is the destination city
        System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Now, please enter the << DESTINATION >> city: ");
        Scanner input_city2= new Scanner(System.in);
        String goal= input_city2.nextLine();
        
        /// For loop to check if the input city is VALID
        for(int i=0; i< NumCities ;i++){
        	if(matrix_c[i][i].Source.equalsIgnoreCase(goal)) {
        		/// storing the index of the city in the in_dest variable
        		in_dest=i;
        		Flag_dest=1;
        		
        	}
        }
        while(Flag_dest==0){
        	/// When the city is not valid
        	/// the while loop will continue until the user write a VALID CITY NAME
        	System.out.println("Sorry, You have entered wrong DESTINATION:( , Please Try again!!");
        	System.out.println("So please enter the << DESTINATION >> city: ");
            input_city2= new Scanner(System.in);
            goal= input_city2.nextLine();
        	for(int i=0; i< NumCities ;i++){
            	if(matrix_c[i][i].Destination.equalsIgnoreCase(goal)) {
            		in_dest=i;
            		Flag_dest=1;
            		/// Break from the loop
            		break;
                }	
            }	
        }
        
       /// -------------------------Choosing the searching algorithm--------------------------------------------///
       while(Flag_algo==0){
    	   
    	   
    	   System.out.println("*************************************************************************************");
           System.out.println("Please choose one of the following algorithm to implement(By number):                ");
           System.out.println(" 1. Depth First Search (DFS). ");
           System.out.println(" 2. Breadth First Search (BFS). ");
           System.out.println(" 3. A*. ");
           System.out.println("*************************************************************************************");
           Scanner algorithm= new Scanner(System.in);
           int algo_num= algorithm.nextInt();
           
           if(algo_num == 1) {
        	   
        	   /// The first algorithm is DFS
        	   System.out.println(" ----------------------<< Depth First Search (DFS) >>----------------------------");
        	   System.out.println("The path from "+start+ " to "+ goal + "is as the following:" );
        	   /// Calling the DFS function
        	   g.DFS_Function(in_src,in_dest);
        	   Flag_algo=1;
        	   
        	   break;
        	   
           }
           else if(algo_num == 2) {
        	   
        	 /// The second algorithm is BFS
        	   System.out.println(" ----------------------<< Breadth First Search (BFS) >>----------------------------");
        	   System.out.println("The path from "+start+ " to "+ goal + "is as the following:" );
        	   /// Calling the BFS function
        	   g.BFS_Function(in_src,in_dest);
        	   Flag_algo=1;
        	   break;
        	   
           }
           
           else if(algo_num == 3) {
        	double Final_Cost=0; 
        	 /// The third algorithm is A*
        	   System.out.println(" ----------------------<< A* Search Algorithm >>----------------------------");
        	   System.out.println("The path from "+start+ " to "+ goal + "is as the following:" );
        	   /// Calling the A* function
        	   Final_Cost= g.a_function(in_src,in_dest);
        	   System.out.println("The final cost --> "+ Final_Cost );
        	   Flag_algo=1; 
        	   break;
           }
         
       }
       /// Closing
       input_city1.close();
       input_city2.close();
       
       System.out.println();
       System.out.println();
       System.out.println("*************************************************************************************");
       System.out.println("*                              THANK YOU!                                           *");
       System.out.println("*************************************************************************************");	
    }
 	
}
		