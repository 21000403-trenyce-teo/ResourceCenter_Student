import java.util.ArrayList;

public class ResourceCentre {

	private static final int OPTION_QUIT = 5;
	private static final int OPTION_VIEW = 1;
	private static final int OPTION_ADD = 2;
	private static final int OPTION_LOAN = 3;
	private static final int OPTION_RETURN = 4;

	public static void main(String[] args) {

		ArrayList<Camcorder> camcorderList = new ArrayList<Camcorder>();
		ArrayList<Chromebook> chromebookList = new ArrayList<Chromebook>();

		camcorderList.add(new Camcorder("CC001", "Sony HDR-CX405", 35));
		camcorderList.add(new Camcorder("CC002", "Panasonic HC-MDH2", 10));
		chromebookList.add(new Chromebook("CB001", "ASUS Chromebook ", "Win 10"));
		chromebookList.add(new Chromebook("CB002", "HP Chromebook", "Win 10"));

		int option = 0;

		while (option != OPTION_QUIT) {

			ResourceCentre.menu();
			option = Helper.readInt("Enter an option > ");

			if (option == OPTION_VIEW){
				// View all items
				ResourceCentre.viewAllCamcorder(camcorderList);
				ResourceCentre.viewAllChromebook(chromebookList);

			} else if (option == OPTION_ADD) {
				// Add a new item
				ResourceCentre.setHeader("ADD");
				itemTypeMenu();

				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == OPTION_VIEW) {
					// Add a camcorder
					Camcorder cc = inputCamcorder();
					ResourceCentre.addCamcorder(camcorderList, cc);
					System.out.println("Camcorder added");

				} else if (itemType == OPTION_ADD) {
					// Add a Chromebook
					Chromebook cb = inputChromebook();
					ResourceCentre.addChromebook(chromebookList, cb);
					System.out.println("Chromebook added");

				} else {
					System.out.println("Invalid type");
				}

			} else if (option == OPTION_LOAN) {
				// Loan item
				ResourceCentre.setHeader("LOAN");
				itemTypeMenu();

				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == OPTION_VIEW) {
					// Loan camcorder
					ResourceCentre.loanCamcorder(camcorderList);
				} else if (itemType == OPTION_ADD) {
					// Loan Chromebook
					ResourceCentre.loanChromebook(chromebookList);
				} else {
					System.out.println("Invalid type");
				}

			} else if (option == OPTION_RETURN) {
				// Return item
				ResourceCentre.setHeader("RETURN");
				itemTypeMenu();

				int itemType = Helper.readInt("Enter option to select item type > ");
				if (itemType == OPTION_VIEW) {
					// Return camcorder
					ResourceCentre.returnCamcorder(camcorderList);
				} else if (itemType == OPTION_ADD) {
					// Return Chromebook
					ResourceCentre.returnChromebook(chromebookList);
				} else {
					System.out.println("Invalid type");
				}

			} else if (option == OPTION_QUIZ) {
				System.out.println("Bye!");
			} else {
				System.out.println("Invalid option");
			}

		}

	}

	public static void menu() {
		ResourceCentre.setHeader("RESOURCE CENTRE APP");
		System.out.println("1. Display Inventory");
		System.out.println("2. Add item");
		System.out.println("3. Loan item");
		System.out.println("4. Return item");
		System.out.println("5. Quit");
		Helper.line(80, "-");

	}

	public static void setHeader(String header) {
		Helper.line(80, "-");
		System.out.println(header);
		Helper.line(80, "-");
	}

	public static String showAvailability(boolean isAvailable) {
		String avail;

		if (isAvailable == true) {
			avail = "Yes";
		} else {
			avail = "No";
		}
		return avail;
	}

	// ================================= Option 1 View items (CRUD- Read)
	// =================================
	public static String retrieveAllCamcorder(ArrayList<Camcorder> camcorderList) {
		String output = "";

		for (int i = 0; i < camcorderList.size(); i++) {

			output += String.format("%-10s %-30s %-10s %-10s %-20d\n", camcorderList.get(i).getAssetTag(),
					camcorderList.get(i).getDescription(),
					ResourceCentre.showAvailability(camcorderList.get(i).getIsAvailable()),
					camcorderList.get(i).getDueDate(), camcorderList.get(i).getOpticalZoom());
		}
		return output;
	}

	public static void viewAllCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.setHeader("CAMCORDER LIST");
		String output = String.format("%-10s %-30s %-10s %-10s %-20s\n", "ASSET TAG", "DESCRIPTION", "AVAILABLE",
				"DUE DATE", "OPTICAL ZOOM");
		output += retrieveAllCamcorder(camcorderList);
		System.out.println(output);
	}

	public static String retrieveAllChromebook(ArrayList<Chromebook> chromebookList) {
		String output = "";
		// write your code here
		for (int i = 0; i < chromebookList.size(); i++) {

			output += String.format("%-10s %-30s %-10s %-10s %-20s\n", chromebookList.get(i).getAssetTag(),
					chromebookList.get(i).getDescription(),
					ResourceCentre.showAvailability(chromebookList.get(i).getIsAvailable()),
					chromebookList.get(i).getDueDate(), chromebookList.get(i).getOs());
		}
		return output;
	}

	public static void viewAllChromebook(ArrayList<Chromebook> chromebookList) {

		String output = retrieveAllChromebook(chromebookList);
		System.out.println(output);
	}

	// ================================= Option 2 Add an item (CRUD - Create)
	// =================================
	public static Camcorder inputCamcorder() {
		String tag = Helper.readString("Enter asset tag > ");
		String description = Helper.readString("Enter description > ");
		int zoom = Helper.readInt("Enter optical zoom > ");

		Camcorder cc = new Camcorder(tag, description, zoom);
		return cc;

	}

	public static void addCamcorder(ArrayList<Camcorder> camcorderList, Camcorder cc) {

		camcorderList.add(cc);

	}

	public static Chromebook inputChromebook() {
		// write your code here
		String tag = Helper.readString("Enter asset tag > ");
		String description = Helper.readString("Enter description > ");
		String os = Helper.readString("Enter optical zoom > ");

		Chromebook cb = new Chromebook(tag, description, os);

		return cb;

	}

	public static void addChromebook(ArrayList<Chromebook> chromebookList, Chromebook cb) {
		// write your code here
		chromebookList.add(cb);
	}

	// ================================= Option 3 Loan an item (CRUD - Update)
	// =================================
	public static boolean doLoanCamcorder(ArrayList<Camcorder> camcorderList, String tag, String dueDate) {

		boolean isLoaned = false;

		for (int i = 0; i < camcorderList.size(); i++) {
			if (tag.equalsIgnoreCase(camcorderList.get(i).getAssetTag())
					&& camcorderList.get(i).getIsAvailable() == true) {

				camcorderList.get(i).setIsAvailable(false);
				camcorderList.get(i).setDueDate(dueDate);

				isLoaned = true;

			}
		}
		return isLoaned;
	}

	public static void loanCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.viewAllCamcorder(camcorderList);
		String tag = Helper.readString("Enter asset tag > ");
		String due = Helper.readString("Enter due date > ");
		Boolean isLoaned = doLoanCamcorder(camcorderList, tag, due);
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Camcorder " + tag + " loaned out");
		}
	}

	public static boolean doLoanChromebook(ArrayList<Chromebook> chromebookList, String tag, String dueDate) {
		// write your code here
		boolean isLoaned = false;

		for (int i = 0; i < chromebookList.size(); i++) {
			if (tag.equalsIgnoreCase(chromebookList.get(i).getAssetTag())
					&& chromebookList.get(i).getIsAvailable() == true) {

				chromebookList.get(i).setIsAvailable(false);
				chromebookList.get(i).setDueDate(dueDate);

				isLoaned = true;

			}
		}

		return isLoaned;

	}

	public static void loanChromebook(ArrayList<Chromebook> chromebookList) {
		// write your code here
		ResourceCentre.viewAllChromebook(chromebookList);
		String tag = Helper.readString("Enter asset tag > ");
		String due = Helper.readString("Enter due date > ");
		Boolean isLoaned = doLoanChromebook(chromebookList, tag, due);
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Chromebook " + tag + " loaned out");
		}

	}

	// ================================= Option 4 Return an item (CRUD -
	// Update)=================================
	public static boolean doReturnCamcorder(ArrayList<Camcorder> camcorderList, String tag) {
		boolean isReturned = false;

		for (int i = 0; i < camcorderList.size(); i++) {
			String assetTag = camcorderList.get(i).getAssetTag();
			if (tag.equalsIgnoreCase(assetTag) && camcorderList.get(i).getIsAvailable() == false) {
				camcorderList.get(i).setIsAvailable(true);
				camcorderList.get(i).setDueDate("");
				isReturned = true;

			}
		}
		return isReturned;

	}

	public static void returnCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.viewAllCamcorder(camcorderList);
		String tag = Helper.readString("Enter asset tag > ");
		Boolean isReturned = doReturnCamcorder(camcorderList, tag);

		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Camcorder " + tag + " returned");
		}
	}

	public static boolean doReturnChromebook(ArrayList<Chromebook> chromebookList, String tag) {
		boolean isReturned = false;
		// write your code here

		for (int i = 0; i < chromebookList.size(); i++) {
			String assetTag = chromebookList.get(i).getAssetTag();
			if (tag.equalsIgnoreCase(assetTag) && chromebookList.get(i).getIsAvailable() == false) {
				chromebookList.get(i).setIsAvailable(true);
				chromebookList.get(i).setDueDate("");
				isReturned = true;

			}
		}
		return isReturned;

	}

	public static void returnChromebook(ArrayList<Chromebook> chromebookList) {
		// write your code here
		ResourceCentre.viewAllChromebook(chromebookList);
		String tag = Helper.readString("Enter asset tag > ");
		Boolean isReturned = doReturnChromebook(chromebookList, tag);

		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Camcorder " + tag + " returned");
		}

	}

}
