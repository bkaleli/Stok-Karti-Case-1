package view;

public class StokModel {
	


	private String stokKodu;
	private String stokAdı;
	private int stokTipi;
	private String birimi;
	private String barkodu;
	private double kdvTipi;
	private String acıklama;
	private String olusturmaTarihi;
	
	
	
	public StokModel() {
	}


	public StokModel(String stokKodu, String stokAdı, int stokTipi, String birimi, String barkodu, double kdvTipi, String acıklama,
			String olusturmaTarihi) {
		super();
		this.stokKodu = stokKodu;
		this.stokAdı = stokAdı;
		this.stokTipi = stokTipi;
		this.birimi = birimi;
		this.barkodu = barkodu;
		this.kdvTipi = kdvTipi;
		this.acıklama = acıklama;
		this.olusturmaTarihi = olusturmaTarihi;
		
	}



	
	
	
	public String getStokKodu() {
		return stokKodu;
	}



	public void setStokKodu(String stokKodu) {
		this.stokKodu = stokKodu;
	}



	public String getStokAdı() {
		return stokAdı;
	}



	public void setStokAdı(String stokAdı) {
		this.stokAdı = stokAdı;
	}



	public int getStokTipi() {
		return stokTipi;
	}



	public void setStokTipi(int stokTipi) {
		this.stokTipi = stokTipi;
	}



	public String getBirimi() {
		return birimi;
	}



	public void setBirimi(String birimi) {
		this.birimi = birimi;
	}



	public String getBarkodu() {
		return barkodu;
	}



	public void setBarkodu(String barkodu) {
		this.barkodu = barkodu;
	}



	public double getKdvTipi() {
		return kdvTipi;
	}



	public void setKdvTipi(double kdvTipi) {
		this.kdvTipi = kdvTipi;
	}



	public String getAcıklama() {
		return acıklama;
	}



	public void setAcıklama(String acıklama) {
		this.acıklama = acıklama;
	}



	public String getOlusturmaTarihi() {
		return olusturmaTarihi;
	}



	public void setOlusturmaTarihi(String olusturmaTarihi) {
		this.olusturmaTarihi = olusturmaTarihi;
	}

	

}
