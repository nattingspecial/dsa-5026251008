package lw01.prelab;

public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int pageCharge;

        if (pages <= 10) {
            pageCharge = pages * 1500;
        } else {
            pageCharge = (10 * 1500) + ((pages - 10) * 1000);
        }

        return pageCharge + 2000; // IDR 2000 setup cost
    }

    @Override
    public String label() {
        return "Colour";
    }
}