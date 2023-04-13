package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

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

	public void setSpouse(String newSpouseName, String newSpouseIDNumber) {
		this.spouseName = newSpouseName;
		this.spouseIdNumber = newSpouseIDNumber;
	}

	public void addChild(String newChildName, String newChildIdNumber) {
		this.childNames.add(newChildName);
		this.childIdNumbers.add(newChildIdNumber);
	}
}

public class Employee {

	private EmployeeIdentityInformation profile;
	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private boolean gender; //true = Laki-laki, false = Perempuan
	
	private EmployeeIncomeInformation income;
	
	private EmployeeFamilyInformation familyInformation;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = 3000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}else if (grade == 2) {
			monthlySalary = 5000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}else if (grade == 3) {
			monthlySalary = 7000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
