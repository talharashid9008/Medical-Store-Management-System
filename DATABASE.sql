### Create database 
create database Medical_Store_Management_System;
use Medical_Store_Management_System;

#drop tables
drop table Medical_Store_Management_System;
drop table Store_Pharamcist;
drop table Store_SalesMan;
drop table Medicines;

#-------------------------create pharmacist table----------------------------------------------
create table Store_Pharamcist(
		pharmacist_CNIC int primary key,
        pharmacist_Name varchar(100) not null,
        pharmacist_Address varchar(100) not null,
        pharmacist_DOB date not null,
        pharmacist_PhoneNO varchar(100) not null,
        pharmacist_LicenseNo varchar(50) not null,
        pharmacist_Login_UserName varchar(100) not null,
        pharmacist_Login_Password varchar(100) not null
);

#-------------------------create sales man table----------------------------------------------
create table Store_SalesMan(
		salesman_CNIC int primary key,
        salesman_Name varchar(100) not null,
        salesman_Address varchar(100) not null,
        salesman_DOB date not null,
        salesman_PhoneNO varchar(100) not null,
        salesman_Login_UserName varchar(100) not null,
        salesman_Login_Password varchar(100) not null
);

#-------------------------create sales man table----------------------------------------------
create table Store_Admins(
		admin_CNIC int primary key,
        admin_name varchar(100) not null,
        admin_Address varchar(100) not null,
        admin_Login_UserName varchar(100) not null,
        admin_Login_Password varchar(100) not null
);

#-------------------- create medicine table -----------------------------------------------
create table Medicines(
		medicine_Drugcode varchar(50) primary key,
        medicine_Name varchar(100) not null,
		medicine_BatchNo varchar(100) not null,
		medicine_Generic_Name varchar(100) not null,
        medicine_RetailPrice int not null,
        medicine_DealerPrice int not null,
        medicine_ManufactureName varchar(100) not null,
        medicine_Dossage varchar(100) not null,
        medicine_DateOf_Manufacture date not null,
        medicine_DateOf_Expiry date not null,
        medicine_Description varchar(250) not null,
        medicine_Type varchar(50) not null,
        medicine_Quantity int not null
);
#--------------------------- create Billing table -------------------------------------------
drop table Bills;
create table Bills(
	billNo varchar(100) primary key,
    customerName varchar(200) not null,
    customerPhoneNo varchar(25) not null,
    paymentMethod varchar(50) not null,
    totalAmountOfBill int not null,
    discountPercentage int not null,
    totalSavingsOnBill float not null,
    totalPayableBill float not null,
    cashGivenByCustomer int not null,
    changeOfCashGivenBackToCustomer int not null,
    dateTimeOfBill datetime,
    salesmanName varchar(100)
);
drop table Medicine_In_Cart;
create table Purchased_Medicines(
	srNo int auto_increment primary key,
    drugCode varchar (50) ,
    medicineName varchar(100),
    batchNo varchar(50),
    medicineExpiryDate date,
    amount int,
    quantity int,
    unitPrice int,
	bill_no varchar(100) ,
	foreign key (bill_no) references Bills(billNo)
);


#---------------------------- SHow Pharmacist Table ------------------------------------------
SELECT * FROM medical_store_management_system.store_pharmacist;
SELECT * FROM medical_store_management_system.Store_SalesMan;
SELECT * FROM medical_store_management_system.store_admin;
SELECT * FROM medical_store_management_system.medicines;


select * from store_admin
where admin_Login_Password="123" and admin_Login_UserName ="uh38041";

#-------medicine update qurey
update medical_store_management_system.medicines set
medical_store_management_system.medicines.medicine_Quantity =25
where medical_store_management_system.medicines.medicine_Drugcode='ABX120';


#----------- Renaming of Columns ----------------------------------------------------------
ALTER TABLE medicines RENAME COLUMN medicine_ManufactureName TO medicine_ManufacturerName;
ALTER TABLE medicines RENAME COLUMN medicine_Drugcode TO medicine_DrugCode;
ALTER TABLE medicines RENAME COLUMN medicine_Drugcode TO medicine_DrugCode;
ALTER TABLE medicines RENAME COLUMN medicine_Dossage  TO medicine_Dosage;

#------------- Adiing Unique Constrraint ------------------------------------------------
ALTER TABLE store_salesman
ADD UNIQUE (salesman_Login_UserName);

ALTER TABLE store_pharmacist
ADD UNIQUE (pharmacist_Login_UserName);


drop table medical_store_management_system.bills_medicine_in_cart;
