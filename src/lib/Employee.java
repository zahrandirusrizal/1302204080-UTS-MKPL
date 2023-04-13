package lib;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private EmployeeIdentityInformation profile;
	private LocalDate dateJoined;
	private int monthWorkingInYear;
	private boolean isForeigner;
	private Gender gender;
	private EmployeeIncomeInformation income;
	private EmployeeFamilyInformation familyInformation;
	
	public Employee(
		EmployeeIdentityInformation newProfile, 
		LocalDate newDateJoined, 
		EmployeeFamilyInformation newFamilyInformation, 
		Gender newGender, 
		boolean newIsForeigner
		) {
		this.profile = newProfile;
		this.dateJoined = newDateJoined;
		this.isForeigner = newIsForeigner;
		this.gender = newGender;
		this.familyInformation = newFamilyInformation;
	}
	
	public void setMonthlySalaryBasedOnGrade() {	
		int ForeignerMonthlySalary;
		if (this.profile.getGrade() == 1) {
			this.income.setMonthlySalary(3000000);
			if (isForeigner) {
				ForeignerMonthlySalary = 3000000 * 1.5;
				this.income.setMonthlySalary(ForeignerMonthlySalary);
			}
		}else if (this.profile.getGrade() == 2) {
			this.income.setMonthlySalary(5000000);
			if (isForeigner) {
				ForeignerMonthlySalary = 5000000 * 1.5;
				this.income.setMonthlySalary(ForeignerMonthlySalary);
			}
		}else if (this.profile.getGrade() == 3) {
			this.income.setMonthlySalary(7000000);
			if (isForeigner) {
				ForeignerMonthlySalary = 5000000 * 1.5;
				this.income.setMonthlySalary(ForeignerMonthlySalary);
			}
		}
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == this.dateJoined.getYear()) {
			this.monthWorkingInYear = date.getMonthValue() - this.dateJoined.getMonthValue();
		}else {
			this.monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(
			this.income.getMonthlySalary(), 
			this.income.getOtherMonthlyIncome(), 
			this.monthWorkingInYear, 
			this.income.getAnnualDeductible(), 
			this.familyInformation.getSpouseIDNumber().equals(""), 
			this.familyInformation.getChildNumbers().size()
			);
	}
}

public class EmployeeIdentityInformation {
	private String employeeId;
	private String firstName;
	private String lastName;
	private int idNumber;
	private String address;
	private int grade;

	public EmployeeIdentityInformation(
		String newEmployeeID.
		String newFirstName,
		String newLastName,
		String newID,
		String newAddress,
		String newGrade
	){
		this.employeeId = newEmployeeID;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.idNumber = newID;
		this.address = newAddress;
		this.grade = newGrade;
	}

	public void setEmployeeID(String newEmployeeID){
		this.newEmployeeID = newEmployeeID;
	}

	public void setFirstName(String newFirstName){
		this.firstName = newFirstName;
	}

	public void setLastName(String newLastName){
		this.lastName = newLastName;
	}

	public void setIDNumber(int newID){
		this.idNumber = newID;
	}

	public void setAddress(String newAddress){
		this.address = newAddress;
	}

	public void setGrade(int newGrade){
		this.grade = newGrade;
	}

	public String getEmployeeID(){
		return this.employeeId;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public String getLastName(){
		return this.lastName;
	}

	public String getIDNumber(){
		return this.idNumber;
	}

	public String getAddress(){
		return this.address;
	}

	public int getGrade(){
		return this.grade;
	}
}

public class EmployeeIncomeInformation{
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	public EmployeeIncomeInformation(
		int newSalary,
		int newOtherMonthlyIncome,
		int newAnnualDeductible
	){
		this.monthlySalary = newSalary;
		this.otherMonthlyIncome = newOtherMonthlyIncome;
		this.annualDeductible = newAnnualDeductible;
	}

	public void setMonthlySalary(int newSalary){
		this.monthlySalary = newSalary;
	}	

	public void setOtherMonthlyIncome(int newOtherMonthlyIncome){
		this.otherMonthlyIncome = newOtherMonthlyIncome;
	}

	public void setAnnualDeductible(int newAnnualDeductible){
		this.annualDeductible = newAnnualDeductible;
	}

	public int getMonthlySalary(){
		return this.monthlySalary;
	}

	public int getOtherMonthlyIncome(){
		return this.otherMonthlyIncome;
	}

	public int getAnnualDeductible(){
		return this.annualDeductible;
	}
}

public class EmployeeFamilyInformation{
	private String spouseName;
	private int spouseIdNumber;
	private List<String> childNames;
	private List<String> childIdNumbers;

	public EmployeeFamilyInformation(
		String newSpouseName,
		int newSpouseIDNumber,
	){
		this.spouseName = newSpouseName;
		this.spouseIdNumber = newSpouseIDNumber;
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}

	public String getSpouseName(){
		return this.spouseName;
	}

	public int getSpouseIDNumber(){
		return this.spouseIdNumber;
	}

	public List<String> getChildNames(){
		return this.childNames;
	}

	public List<String> getChildNumbers(){
		return this.childIdNumbers;
	}

	public void setSpouse(String newSpouseName, String newSpouseIDNumber) {
		this.spouseName = newSpouseName;
		this.spouseIdNumber = newSpouseIDNumber;
	}

	public void addChild(String newChildName, String newChildIdNumber) {
		this.childNames.add(newChildName);
		this.childIdNumbers.add(newChildIdNumber);
	}
}

enum Gender {
	LAKI-LAKI,
	PEREMPUAN
}


