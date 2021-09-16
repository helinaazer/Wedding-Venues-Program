package azer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import numberlist.IndexRangeException;
import numberlist.objectlist.Copiable;
import numberlist.objectlist.Date;
import numberlist.objectlist.NumericObjectArrayList;
import numberlist.primitivelist.IntegerArrayList;
import numberlist.primitivelist.RealArrayList;

/**
 * The Wedding Class
 *
 * @author Chenyu Niu & lydia Soliman
 */
public class Weddings implements Serializable {

    NumericObjectArrayList dates;
    //NumericObjectArrayList moneys;
    RealArrayList moneys;
    IntegerArrayList guestNums;
    ArrayList<String> brideNames;
    ArrayList<String> groomNames;

    /**
     * Constructor
     */
    public Weddings() {
        dates = new NumericObjectArrayList();
        //moneys = new NumericObjectArrayList();
        moneys = new RealArrayList();
        brideNames = new ArrayList();
        groomNames = new ArrayList();
        guestNums = new IntegerArrayList();
    }

    /**
     * Add a wedding
     *
     * @param brideName the new bridge name
     * @param groomName the new groom name
     * @param date the new date
     * @param guestNum the new guest number
     * @param money the new amount of money
     * @throws IndexRangeException
     */
    public void addWeddings(String brideName, String groomName, Date date, long guestNum, double money) throws IndexRangeException {
        brideNames.add(brideName);
        groomNames.add(groomName);
        dates.insert(dates.getCount(), date);
        guestNums.insert(guestNum);
        moneys.insert(moneys.getCount(), money);
    }

    /**
     * Replace a wedding
     *
     * @param index the index to be replaced
     * @param brideName the new bridge name
     * @param groomName the new groom name
     * @param date the new date
     * @param guestNum the new guest number
     * @param money the new amount of money
     * @throws IndexRangeException
     */
    public void replaceWeddings(int index, double money) throws IndexRangeException {
        moneys.replace(index, money);
    }

    /**
     * Delete a weeding
     *
     * @param index the index to delete
     * @throws IndexRangeException
     */
    public void deleteWeddings(int index) throws IndexRangeException {
        brideNames.remove(index);
        groomNames.remove(index);
        dates.deleteAt(index);
        guestNums.delete(index);
        moneys.deleteAt(index);
    }

    /**
     * Get the single date object from the list
     *
     * @param index the index of the object
     * @return The date object for the specified wedding.
     * @throws IndexRangeException
     */
    public Copiable getDate(int index) throws IndexRangeException {
        return dates.getValueAt(index);
    }

    /**
     * Get the single money from the list
     *
     * @param index the index of the object
     * @return The cost of the specified wedding.
     * @throws IndexRangeException
     */
    public double getMoney(int index) throws IndexRangeException {
        return moneys.getValueAt(index);
    }

    /**
     * Get the single number of guests from the list
     *
     * @param index the index of the object
     * @return The number of guests for the selected wedding.
     * @throws IndexRangeException
     */
    public long getGuestNum(int index) throws IndexRangeException {
        return guestNums.getValueAt(index);
    }

    /**
     * Get the bride name from the list
     *
     * @param index the index of the object
     * @return The bride name from a wedding
     */
    public String getBrideName(int index) {
        return brideNames.get(index);
    }

    /**
     * Get the groom name from the list
     *
     * @param index the index of the object
     * @return the groom name from a wedding
     */
    public String getGroomName(int index) {
        return groomNames.get(index);
    }

    /**
     * Getter for the whole list of dates
     *
     * @return dates The NumericObjectArrayList holding all the dates.
     */
    public NumericObjectArrayList getDates() {
        return dates;
    }

    /**
     *Getter for the whole list of money. 
     * 
     * @return moneys The RealArrayList holding all the money. 
     */
    public RealArrayList getMoneys() {
        return moneys;
    }

    /**
     * Getter for the whole list of GuestNums
     *
     * @return list of GuestNums
     */
    public IntegerArrayList getGuestNums() {
        return guestNums;
    }

    /**
     * Getter for the whole list of BrideNames
     *
     * @return list of BrideNames
     */
    public ArrayList<String> getBrideNames() {
        return brideNames;
    }

    /**
     * Getter for the whole list of GroomNames
     *
     * @return list of GroomNames
     */
    public ArrayList<String> getGroomNames() {
        return groomNames;
    }

    /**
     * Swap method for RealArrayList
     *
     * @param arr the arr to swap
     * @param i the index
     * @param j the index to be swapped to
     * @throws numberlist.IndexRangeException
     */
    public void swap(RealArrayList arr, int i, int j) throws IndexRangeException {
        double temp = arr.getValueAt(i);
        arr.replace(i, arr.getValueAt(j));
        arr.replace(j, temp);
    }

    /**
     * Swap method for IntegerArrayList
     *
     * @param arr IntegerArrayList
     * @param i the index
     * @param j the index to be swapped to
     * @throws IndexRangeException
     */
    public void swap(IntegerArrayList arr, int i, int j) throws IndexRangeException {
        long temp = arr.getValueAt(i);
        arr.replace(i, arr.getValueAt(j));
        arr.replace(j, temp);
    }

    /**
     * Swap method for NumericObjectArrayList
     *
     * @param arr NumericObjectArrayList
     * @param i the index
     * @param j the index to be swapped to
     * @throws IndexRangeException
     */
    public void swap(NumericObjectArrayList arr, int i, int j) throws IndexRangeException {
        Copiable temp = arr.getValueAt(i);
        arr.replace(i, arr.getValueAt(j));
        arr.replace(j, temp);
    }

    /**
     * Swap method for ArrayList<String>
     *
     * @param arr ArrayList<String>
     * @param i the index 
     * @param j the index to be swapped to
     */
    public void swap(ArrayList<String> arr, int i, int j) {
        Collections.swap(arr, i, j);
    }

    /**
     * Improved bubble sort for NumericObjectArrayList This sort have time
     * complexity of O(n) and a space complexity of 1 We chose bubble sort
     * because it works well in a small list, stable, and only have a space
     * complexity of 1.
     *
     * @param list NumericObjectArrayList holding all of the objects to be
     * sorted.
     * @return list The sorted version of the NumericObjectArrayList.
     * @throws IndexRangeException
     */
    public NumericObjectArrayList sort(NumericObjectArrayList list) throws IndexRangeException {
        boolean swapped = true;
        for (int k = 0; k < list.getCount() - 1 && swapped; k++) {
            swapped = false;
            for (int i = 0; i < list.getCount() - k - 1; i++) {
                Date temp = (Date) list.getValueAt(i);
                if (temp.compareTo((Date) list.getValueAt(i + 1)) > 0) {
                    swap(dates, i, i + 1);
                    swap(moneys, i, i + 1);
                    swap(guestNums, i, i + 1);
                    swap(brideNames, i, i + 1);
                    swap(groomNames, i, i + 1);
                    swapped = true;
                }
            }
        }
        return list;
    }

    /**
     * Improved bubble sort for RealArrayList This sort have time complexity of
     * O(n) and a space complexity of 1 We chose bubble sort because it works
     * well in a small list, stable, and only have a space complexity of 1.
     *
     * @param list RealArrayList holding all of the values to be sorted.
     * @return list The sorted version of the RealArrayList.
     * @throws IndexRangeException
     */
    public RealArrayList sortMoney(RealArrayList list) throws IndexRangeException {
        boolean swapped = true;
        for (int k = 0; k < list.getCount() - 1 && swapped; k++) {
            swapped = false;
            for (int i = 0; i < list.getCount() - k - 1; i++) {
                double temp = list.getValueAt(i);
                if (temp - (list.getValueAt(i + 1)) > 0) {
                    swap(dates, i, i + 1);
                    swap(moneys, i, i + 1);
                    swap(guestNums, i, i + 1);
                    swap(brideNames, i, i + 1);
                    swap(groomNames, i, i + 1);
                    swapped = true;
                }
            }
        }
        return list;
    }

    /**
     * Improved bubble sort for IntegerArrayList This sort have time complexity
     * of O(n) and a space complexity of 1 We chose bubble sort because it works
     * well in a small list, stable, and only have a space complexity of 1.
     *
     * @param list IntegerArrayList holding all of the values to be sorted.
     * @return list The sorted version of the IntegerArrayList.
     * @throws IndexRangeException
     */
    public IntegerArrayList sort(IntegerArrayList list) throws IndexRangeException {
        boolean swapped = true;
        for (int k = 0; k < list.getCount() - 1 && swapped; k++) {
            swapped = false;
            for (int i = 0; i < list.getCount() - k - 1; i++) {
                long temp = list.getValueAt(i);
                if (temp - (list.getValueAt(i + 1)) > 0) {
                    swap(dates, i, i + 1);
                    swap(moneys, i, i + 1);
                    swap(guestNums, i, i + 1);
                    swap(brideNames, i, i + 1);
                    swap(groomNames, i, i + 1);
                    swapped = true;
                }
            }
        }
        return list;
    }

    /**
     * Sort for ArrayList<String>
     * This sort have time complexity of O(n) and a space complexity of 1 We
     * chose bubble sort because it works well in a small list, stable, and only
     * have a space complexity of 1.
     *
     * @param list ArrayList<String> holding all of the strings to be sorted.
     * @return list The sorted version of the ArrayList<String>.
     * @throws numberlist.IndexRangeException
     */
    public ArrayList<String> sort(ArrayList<String> list) throws IndexRangeException {
        boolean swapped = true;
        for (int k = 0; k < list.size() - 1 && swapped; k++) {
            swapped = false;
            for (int i = 0; i < list.size() - k - 1; i++) {
                String temp = list.get(i);
                if (Character.toUpperCase((temp.charAt(0))) - (Character.toUpperCase(list.get(i + 1).charAt(0))) > 0) {
                    swap(dates, i, i + 1);
                    swap(moneys, i, i + 1);
                    swap(guestNums, i, i + 1);
                    swap(brideNames, i, i + 1);
                    swap(groomNames, i, i + 1);
                    swapped = true;
                }
            }
        }
        return list;
    }

    /**
     * This method returns a string containing the details of the wedding.
     *
     * @return s The string containing each wedding's details.
     */
    @Override
    public String toString() {
        String s = "";
        // String brideName, String groomName, Date date, int guestNum, double money
        for (int i = 0; i < brideNames.size(); i++) {
            try {
                s += brideNames.get(i) + "," + groomNames.get(i) + "," + dates.getValueAt(i).toString() + ","
                        + guestNums.getValueAt(i) + "," + moneys.getValueAt(i) + "\n";
            } catch (IndexRangeException ex) {
                Logger.getLogger(Weddings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return s;
    }
}
