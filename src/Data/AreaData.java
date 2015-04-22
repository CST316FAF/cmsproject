/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Davis
 */
public class AreaData {
    private int customerNumber = 0;
    private int jobNumber = 0;
    private int areaCode = 0;

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }
   
    public void incrementCustomerNumber() {
        customerNumber+=1;
    }
    public void incrementJobNumber() {
        jobNumber+=1;
    }
    
    
    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }
    
    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

}
