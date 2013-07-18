package eu.veldsoft.marias;

/**
 * Class Hra describes goals during the game.
 */
class Hra {
	public boolean farba;

	public int tromf;
	public int flekNaHru;

	public boolean sedma;
	public boolean sedmaProti;
	public int flekNaSedmu;

	public boolean stovka;
	public boolean stovkaProti;
	public int flekNaStovku;

	public boolean durch;
	public boolean betl;
	public int zlaFarbaPid;
	public int flekNaDurch;
	public int flekNaBetl;

	public int forhontPoints;
	public int oppPoints;
	public int forhontHlasky;
	public int oppHlasky;

	public Hra() {
		farba = true;
		tromf = -1;
		flekNaHru = 0;

		sedma = false;
		sedmaProti = false;
		flekNaSedmu = 0;

		stovka = false;
		stovkaProti = false;
		flekNaStovku = 0;

		durch = false;
		betl = false;
		zlaFarbaPid = 0;
		flekNaDurch = 0;
		flekNaBetl = 0;

		forhontPoints = 0;
		oppPoints = 0;
		forhontHlasky = 0;
		oppHlasky = 0;
	}

	public int tromf7() {
		return 8 * (tromf / 8);
	}
}
