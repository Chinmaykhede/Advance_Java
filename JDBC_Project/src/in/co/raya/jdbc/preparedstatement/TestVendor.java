package in.co.raya.jdbc.preparedstatement;

public class TestVendor {
	public static void main(String[] args) throws Exception {
		//testcreate();
		//testAdd();
		//testdelete();
		//testupdate();
	}

	private static void testupdate() throws Exception{
		VendorModel pm = new VendorModel();
		BeanVendor bp = new BeanVendor();
		bp.setVendorName("Yuvraj Sound System ");
		bp.setVendorId(105);
		pm.update(bp);
		
	}

	private static void testAdd()throws Exception {
		VendorModel vm = new VendorModel();
		BeanVendor bm = new BeanVendor();
		bm.setVendorId(112);
		bm.setVendorName("Creative Events");
		bm.setMobileNo("9345612789");
		bm.setAddress("Ratlam");
		bm.setServiceType("Event Management");
		vm.add(bm);
	}

	private static void testdelete() throws Exception {
		VendorModel vm = new VendorModel();
		BeanVendor bp = new BeanVendor();
		bp.setVendorId(112);
		vm.delete(bp);

		
	}

	private static void testcreate() throws Exception {
		VendorModel vm = new VendorModel();
		vm.create();
	}

}
