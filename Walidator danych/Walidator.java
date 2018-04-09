package Pozyczki;

public class Walidator {
	static byte[] peseltabl = new byte[11];
	static byte[] niptabl = new byte[9];

	public static void peselToTable(String pesel) {
		for (int i = 0; i < 11; i++) {
			peseltabl[i] = Byte.parseByte(pesel.substring(i, i + 1));
		}
	}

	public static void nipToTable(String nip) {
		for (int i = 0; i < 9; i++) {
			niptabl[i] = Byte.parseByte(nip.substring(i, i + 1));
		}
	}

	public static int getRokUrodzenia() {
		int rok;
		int miesiac;
		rok = 10 * peseltabl[0];
		rok += peseltabl[1];
		miesiac = 10 * peseltabl[2];
		miesiac += peseltabl[3];
		if (miesiac > 0 && miesiac < 13) {
			rok += 1900;
		} else {
			rok += 2000;
		}
		return rok;
	}

	public static int getMiesiacUrodzenia() {
		int miesiac;
		miesiac = 10 * peseltabl[2];
		miesiac += peseltabl[3];
		if (miesiac > 20 && miesiac < 33) {
			miesiac = miesiac - 20;
		}
		return miesiac;
	}

	public static int getDzienUrodzenia() {
		int dzien;
		dzien = 10 * peseltabl[4];
		dzien += peseltabl[5];
		return dzien;
	}

	public static String plec() {
		if (peseltabl[9] % 2 == 0) {
			return "K";
		} else {
			return "M";
		}
	}

	public static boolean checkSumPesel() {
		int sum;
		sum = 1 * peseltabl[0] + 3 * peseltabl[1] + 7 * peseltabl[2] + 9 * peseltabl[3] + 1 * peseltabl[4]
				+ 3 * peseltabl[5] + 7 * peseltabl[6] + 9 * peseltabl[7] + 1 * peseltabl[8] + 3 * peseltabl[9];
		sum %= 10;
		sum = 10 - sum;
		sum %= 10;
		if (sum == peseltabl[10]) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkSumNip() {
		int sum;
		sum = 6 * niptabl[0] + 5 * niptabl[1] + 7 * niptabl[2] + 2 * niptabl[3] + 3 * niptabl[4] + 4 * niptabl[5]
				+ 5 * niptabl[6] + 6 * niptabl[7] + 7 * niptabl[8];
		sum = sum % 11;
		if (sum == niptabl[9]) {
			return true;
		} else {
			return false;
		}
	}
}
