package model;

public class Person {
    	private String firstName;
    	private String lastName;
    	private String gender;
    	private String country;
    	private String age;
    	private String date;
    	private String id;
    	
    	public Person(String firstName,String lastName,String gender, String country,String age, String date,String id ) {
    	    this.firstName = firstName;
    	    this.lastName = lastName;
    	    this.gender = gender;
    	    this.country = country;
    	    this.age = age;
    	    this.date = date;
    	    this.id = id;
    	}
    	
    	@Override
	public String toString() {
	    return "Person [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", country="
		    + country + ", age=" + age + ", date=" + date + ", id=" + id + "]";
	}

	public String getLastName() {
	    return lastName;
	}
	public void setLastName(String lastName) {
	    this.lastName = lastName;
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
	public String getAge() {
	    return age;
	}
	public void setAge(String age) {
	    this.age = age;
	}
	public String getDate() {
	    return date;
	}
	public void setDate(String date) {
	    this.date = date;
	}
	public String getId() {
	    return id;
	}
	public void setId(String id) {
	    this.id = id;
	}
	public String getFirstName() {
	    return firstName;
	}
	public void setFirstName(String firstName) {
	    this.firstName = firstName;
	}
}
