package com.example.pdfgeneration.model;

import java.util.List;

public class Invoice {
    private String seller;
    private String sellerGstin;
    private String sellerAddress;
    private String buyer;
    private String buyerGstin;
    private String buyerAddress;
    private List<Item> items;


	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}


	public String getSellerGstin() {
		return sellerGstin;
	}


	public void setSellerGstin(String sellerGstin) {
		this.sellerGstin = sellerGstin;
	}


	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}


	public String getBuyer() {
		return buyer;
	}


	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}


	public String getBuyerGstin() {
		return buyerGstin;
	}


	public void setBuyerGstin(String buyerGstin) {
		this.buyerGstin = buyerGstin;
	}


	public String getBuyerAddress() {
		return buyerAddress;
	}


	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
    public String toString() {
        return "Invoice{" +
                "seller='" + seller + '\'' +
                ", sellerGstin='" + sellerGstin + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", buyer='" + buyer + '\'' +
                ", buyerGstin='" + buyerGstin + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                ", items=" + items +
                '}';
    }

	public static class Item {
        private String name;
        private String quantity;
        private double rate;
        private double amount;
        
        
        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", quantity='" + quantity + '\'' +
                    ", rate=" + rate +
                    ", amount=" + amount +
                    '}';
        }
        
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
		public double getRate() {
			return rate;
		}
		public void setRate(double rate) {
			this.rate = rate;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
        
    }
}
