package com.liaojl.netty.http.xml.pojo;

public class Order {
    private long orderNumber;
    private Customer customer;
    private Address billTo;
    private Shipping shipping;
    private Address shipTo;
    private Float total;

    public Order() {
    }

    public long getOrderNumber() {
        return this.orderNumber;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Address getBillTo() {
        return this.billTo;
    }

    public Shipping getShipping() {
        return this.shipping;
    }

    public Address getShipTo() {
        return this.shipTo;
    }

    public Float getTotal() {
        return this.total;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBillTo(Address billTo) {
        this.billTo = billTo;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        final Order other = (Order) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        if (this.getOrderNumber() != other.getOrderNumber()) {
            return false;
        }
        final Object this$customer = this.getCustomer();
        final Object other$customer = other.getCustomer();
        if (this$customer == null ? other$customer != null : !this$customer.equals(other$customer)) {
            return false;
        }
        final Object this$billTo = this.getBillTo();
        final Object other$billTo = other.getBillTo();
        if (this$billTo == null ? other$billTo != null : !this$billTo.equals(other$billTo)) {
            return false;
        }
        final Object this$shipping = this.getShipping();
        final Object other$shipping = other.getShipping();
        if (this$shipping == null ? other$shipping != null : !this$shipping.equals(other$shipping)) {
            return false;
        }
        final Object this$shipTo = this.getShipTo();
        final Object other$shipTo = other.getShipTo();
        if (this$shipTo == null ? other$shipTo != null : !this$shipTo.equals(other$shipTo)) {
            return false;
        }
        final Object this$total = this.getTotal();
        final Object other$total = other.getTotal();
        if (this$total == null ? other$total != null : !this$total.equals(other$total)) {
            return false;
        }
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Order;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $orderNumber = this.getOrderNumber();
        result = result * PRIME + (int) ($orderNumber >>> 32 ^ $orderNumber);
        final Object $customer = this.getCustomer();
        result = result * PRIME + ($customer == null ? 43 : $customer.hashCode());
        final Object $billTo = this.getBillTo();
        result = result * PRIME + ($billTo == null ? 43 : $billTo.hashCode());
        final Object $shipping = this.getShipping();
        result = result * PRIME + ($shipping == null ? 43 : $shipping.hashCode());
        final Object $shipTo = this.getShipTo();
        result = result * PRIME + ($shipTo == null ? 43 : $shipTo.hashCode());
        final Object $total = this.getTotal();
        result = result * PRIME + ($total == null ? 43 : $total.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Order(orderNumber=" + this.getOrderNumber() + ", customer=" + this.getCustomer() + ", billTo=" + this.getBillTo() + ", shipping=" + this.getShipping() + ", shipTo=" + this.getShipTo() + ", total=" + this.getTotal() + ")";
    }
}