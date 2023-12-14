public class City {
	
	/// The city class attributes.
		/// The source city 
		/// considered as the start node
		String Source;
		/// The Destination city
		/// considered as the goal node 
		String Destination;
		/// The cost is the car distance between two connected cities or nodes
		int car_cost;
		/// The first heuristic from the source node to the destination 
		/// represent the walking distance
		int h_walk;
		/// The second heuristic from the source node to the destination
		/// represent the straight line from the source node to the destination 
		int h_areal;
		
		/// Initializing constructors
		public City() {
			
		}
	   
		public City(String Source,String Destination,int car_cost,int h_walk,int h_areal) {
			
			this.Destination=Destination;
			this.Source=Source;
			this.car_cost=car_cost;
			this.h_walk=h_walk;
			this.h_areal=h_areal;
		
		}
		
		/// Generating getters and setters
		public String getSource() {
			return Source;
		}
		
		public void setSource(String source) {
			Source = source;
		}
		
		public String getDestination() {
			return Destination;
		}
		
		public void setDestination(String destination) {
			Destination = destination;
		}
		
		public int getCar_cost() {
			return car_cost;
		}
		
		public void setCar_cost(int car_cost) {
			this.car_cost = car_cost;
		}
		
		public int getH_walk() {
			return h_walk;
		}
		
		public void setH_walk(int h_walk) {
			this.h_walk = h_walk;
		}
		
		public int getH_areal() {
			return h_areal;
		}
		public void setH_areal(int h_areal) {
			this.h_areal = h_areal;
		}
		
	 
	    

}
