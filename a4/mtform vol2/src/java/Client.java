
public class Client {

	    private String uname;
	    private String email;
	    private String password;
	    private String varify;
	    private String fname;
	    private String sname;
	    private String dt;
	    private String gender;
	    private String country;
	    private String city;
	    private String minfo;
	
	    
	    public Client(String uname, String email, String password, String varify, String fname, String sname, String dt, String gender, String country, String city, String minfo){
	        
	    	 this.uname = uname;
                 this.password = password;
	    	 this.email = email;
	    	 this.varify = varify;
	    	 this.fname = fname;
	    	 this.sname = sname;
	    	 this.dt = dt;
	    	 this.gender = gender;
	    	 this.country = country;
	    	 this.city = city;
	    	 this.minfo = minfo;
	    
	    }

	    
	    public Client() {
	    	this.uname= "";
	    }
	    

		public String getUname() {
			return uname;
		}


		public void setUname(String uname) {
			this.uname = uname;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getVarify() {
			return varify;
		}


		public void setVarify(String varify) {
			this.varify = varify;
		}


		public String getFname() {
			return fname;
		}


		public void setFname(String fname) {
			this.fname = fname;
		}


		public String getSname() {
			return sname;
		}


		public void setSname(String sname) {
			this.sname = sname;
		}


		public String getDt() {
			return dt;
		}


		public void setDt(String dt) {
			this.dt = dt;
		}


		public String getGender() {
			return gender;
		}


		public void setGender(String gender) {
			this.gender = gender;
		}


		public String getCountry() {
			return country;
		}


		public void setCountry(String country) {
			this.country = country;
		}


		public String getCity() {
			return city;
		}


		public void setCity(String city) {
			this.city = city;
		}


		public String getMinfo() {
			return minfo;
		}


		public void setMinfo(String minfo) {
			this.minfo = minfo;
		}
	    
	    
}
