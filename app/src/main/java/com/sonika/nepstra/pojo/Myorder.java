package com.sonika.nepstra.pojo;

import org.json.JSONArray;

/**
 * Created by Prakriti on 12/5/2017.
 */

public class Myorder {
    public transient JSONArray line_item_taxes, line_item_meta_data, tax_lines, tax_lines_taxes,
            fee_lines, coupon_lines, refunds;
    public Boolean prices_include_tax, date_paid, date_paid_gmt, date_completed, date_completed_gmt, prefix,
            auffix, padding;
    public String number, order_key, created_via, version, status, currence, date_created, date_created_gmt,
            date_modified, date_modified_gmt, discount_total, discount_tax, shipping_tax, shipping_total,
            cart_tax, total, total_tax, customer_ip_address, customer_user_agent, customer_note,
            billing_fname, billing_last_name, billing_company, billing_address_1, billing_adddress_2, billing_city,
            billing_state, billing_postcode, billing_country, billing_email, billing_phone, shipping_fname,
            shipping_last_name, shipping_company, shippingbilling_address_1, shipping_adddress_2, shipping_city,
            shipping_state, shipping_postcode, shipping_country, payment_method, payment_method_title, transaction_id,
            cart_hash,
            meta_data_key, meta_data_value, meta_data_value_formatted_number, meta_data_value_document_type, line_item_name, line_item_tax_class,
            line_item_subtotal, line_item_subtotal_tax, line_item_total, line_item_total_tax, sku,
            shipping_method_title,
            shipping_method_id,
            shipping_line_total, shipping_total_taxes, shipping_meta_data_key, shipping_meta_data_value, links_self_href,
            links_collection_href,
            links_customer_href;
    public Integer id, parent_id, customer_id, meta_id,
            line_id, line_quantity, line_product_id, line_variation_id,
            line_price, shipping_lines_id, shipping_meta_data_id, meta_data_value_number, meta_data_value_order_id;

    public Myorder() {
    }

    public Myorder(JSONArray line_item_taxes, JSONArray line_item_meta_data, JSONArray tax_lines, JSONArray tax_lines_taxes, JSONArray fee_lines, JSONArray coupon_lines, JSONArray refunds, Boolean prices_include_tax, Boolean date_paid, Boolean date_paid_gmt, Boolean date_completed, Boolean date_completed_gmt, Boolean prefix, Boolean auffix, Boolean padding, String number, String order_key, String created_via, String version, String status, String currence, String date_created, String date_created_gmt, String date_modified, String date_modified_gmt, String discount_total, String discount_tax, String shipping_tax, String shipping_total, String cart_tax, String total, String total_tax, String customer_ip_address, String customer_user_agent, String customer_note, String billing_fname, String billing_last_name, String billing_company, String billing_address_1, String billing_adddress_2, String billing_city, String billing_state, String billing_postcode, String billing_country, String billing_email, String billing_phone, String shipping_fname, String shipping_last_name, String shipping_company, String shippingbilling_address_1, String shipping_adddress_2, String shipping_city, String shipping_state, String shipping_postcode, String shipping_country, String payment_method, String payment_method_title, String transaction_id, String cart_hash, String meta_data_key, String meta_data_value, String meta_data_value_formatted_number, String meta_data_value_document_type, String line_item_name, String line_item_tax_class, String line_item_subtotal, String line_item_subtotal_tax, String line_item_total, String line_item_total_tax, String sku, String shipping_method_title, String shipping_method_id, String shipping_line_total, String shipping_total_taxes, String shipping_meta_data_key, String shipping_meta_data_value, String links_self_href, String links_collection_href, String links_customer_href, Integer id, Integer parent_id, Integer customer_id, Integer meta_id, Integer line_id, Integer line_quantity, Integer line_product_id, Integer line_variation_id, Integer line_price, Integer shipping_lines_id, Integer shipping_meta_data_id, Integer meta_data_value_number, Integer meta_data_value_order_id) {
        this.line_item_taxes = line_item_taxes;
        this.line_item_meta_data = line_item_meta_data;
        this.tax_lines = tax_lines;
        this.tax_lines_taxes = tax_lines_taxes;
        this.fee_lines = fee_lines;
        this.coupon_lines = coupon_lines;
        this.refunds = refunds;
        this.prices_include_tax = prices_include_tax;
        this.date_paid = date_paid;
        this.date_paid_gmt = date_paid_gmt;
        this.date_completed = date_completed;
        this.date_completed_gmt = date_completed_gmt;
        this.prefix = prefix;
        this.auffix = auffix;
        this.padding = padding;
        this.number = number;
        this.order_key = order_key;
        this.created_via = created_via;
        this.version = version;
        this.status = status;
        this.currence = currence;
        this.date_created = date_created;
        this.date_created_gmt = date_created_gmt;
        this.date_modified = date_modified;
        this.date_modified_gmt = date_modified_gmt;
        this.discount_total = discount_total;
        this.discount_tax = discount_tax;
        this.shipping_tax = shipping_tax;
        this.shipping_total = shipping_total;
        this.cart_tax = cart_tax;
        this.total = total;
        this.total_tax = total_tax;
        this.customer_ip_address = customer_ip_address;
        this.customer_user_agent = customer_user_agent;
        this.customer_note = customer_note;
        this.billing_fname = billing_fname;
        this.billing_last_name = billing_last_name;
        this.billing_company = billing_company;
        this.billing_address_1 = billing_address_1;
        this.billing_adddress_2 = billing_adddress_2;
        this.billing_city = billing_city;
        this.billing_state = billing_state;
        this.billing_postcode = billing_postcode;
        this.billing_country = billing_country;
        this.billing_email = billing_email;
        this.billing_phone = billing_phone;
        this.shipping_fname = shipping_fname;
        this.shipping_last_name = shipping_last_name;
        this.shipping_company = shipping_company;
        this.shippingbilling_address_1 = shippingbilling_address_1;
        this.shipping_adddress_2 = shipping_adddress_2;
        this.shipping_city = shipping_city;
        this.shipping_state = shipping_state;
        this.shipping_postcode = shipping_postcode;
        this.shipping_country = shipping_country;
        this.payment_method = payment_method;
        this.payment_method_title = payment_method_title;
        this.transaction_id = transaction_id;
        this.cart_hash = cart_hash;
        this.meta_data_key = meta_data_key;
        this.meta_data_value = meta_data_value;
        this.meta_data_value_formatted_number = meta_data_value_formatted_number;
        this.meta_data_value_document_type = meta_data_value_document_type;
        this.line_item_name = line_item_name;
        this.line_item_tax_class = line_item_tax_class;
        this.line_item_subtotal = line_item_subtotal;
        this.line_item_subtotal_tax = line_item_subtotal_tax;
        this.line_item_total = line_item_total;
        this.line_item_total_tax = line_item_total_tax;
        this.sku = sku;
        this.shipping_method_title = shipping_method_title;
        this.shipping_method_id = shipping_method_id;
        this.shipping_line_total = shipping_line_total;
        this.shipping_total_taxes = shipping_total_taxes;
        this.shipping_meta_data_key = shipping_meta_data_key;
        this.shipping_meta_data_value = shipping_meta_data_value;
        this.links_self_href = links_self_href;
        this.links_collection_href = links_collection_href;
        this.links_customer_href = links_customer_href;
        this.id = id;
        this.parent_id = parent_id;
        this.customer_id = customer_id;
        this.meta_id = meta_id;
        this.line_id = line_id;
        this.line_quantity = line_quantity;
        this.line_product_id = line_product_id;
        this.line_variation_id = line_variation_id;
        this.line_price = line_price;
        this.shipping_lines_id = shipping_lines_id;
        this.shipping_meta_data_id = shipping_meta_data_id;
        this.meta_data_value_number = meta_data_value_number;
        this.meta_data_value_order_id = meta_data_value_order_id;
    }

    public JSONArray getLine_item_taxes() {
        return line_item_taxes;
    }

    public void setLine_item_taxes(JSONArray line_item_taxes) {
        this.line_item_taxes = line_item_taxes;
    }

    public JSONArray getLine_item_meta_data() {
        return line_item_meta_data;
    }

    public void setLine_item_meta_data(JSONArray line_item_meta_data) {
        this.line_item_meta_data = line_item_meta_data;
    }

    public JSONArray getTax_lines() {
        return tax_lines;
    }

    public void setTax_lines(JSONArray tax_lines) {
        this.tax_lines = tax_lines;
    }

    public JSONArray getTax_lines_taxes() {
        return tax_lines_taxes;
    }

    public void setTax_lines_taxes(JSONArray tax_lines_taxes) {
        this.tax_lines_taxes = tax_lines_taxes;
    }

    public JSONArray getFee_lines() {
        return fee_lines;
    }

    public void setFee_lines(JSONArray fee_lines) {
        this.fee_lines = fee_lines;
    }

    public JSONArray getCoupon_lines() {
        return coupon_lines;
    }

    public void setCoupon_lines(JSONArray coupon_lines) {
        this.coupon_lines = coupon_lines;
    }

    public JSONArray getRefunds() {
        return refunds;
    }

    public void setRefunds(JSONArray refunds) {
        this.refunds = refunds;
    }

    public Boolean getPrices_include_tax() {
        return prices_include_tax;
    }

    public void setPrices_include_tax(Boolean prices_include_tax) {
        this.prices_include_tax = prices_include_tax;
    }

    public Boolean getDate_paid() {
        return date_paid;
    }

    public void setDate_paid(Boolean date_paid) {
        this.date_paid = date_paid;
    }

    public Boolean getDate_paid_gmt() {
        return date_paid_gmt;
    }

    public void setDate_paid_gmt(Boolean date_paid_gmt) {
        this.date_paid_gmt = date_paid_gmt;
    }

    public Boolean getDate_completed() {
        return date_completed;
    }

    public void setDate_completed(Boolean date_completed) {
        this.date_completed = date_completed;
    }

    public Boolean getDate_completed_gmt() {
        return date_completed_gmt;
    }

    public void setDate_completed_gmt(Boolean date_completed_gmt) {
        this.date_completed_gmt = date_completed_gmt;
    }

    public Boolean getPrefix() {
        return prefix;
    }

    public void setPrefix(Boolean prefix) {
        this.prefix = prefix;
    }

    public Boolean getAuffix() {
        return auffix;
    }

    public void setAuffix(Boolean auffix) {
        this.auffix = auffix;
    }

    public Boolean getPadding() {
        return padding;
    }

    public void setPadding(Boolean padding) {
        this.padding = padding;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrder_key() {
        return order_key;
    }

    public void setOrder_key(String order_key) {
        this.order_key = order_key;
    }

    public String getCreated_via() {
        return created_via;
    }

    public void setCreated_via(String created_via) {
        this.created_via = created_via;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrence() {
        return currence;
    }

    public void setCurrence(String currence) {
        this.currence = currence;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_created_gmt() {
        return date_created_gmt;
    }

    public void setDate_created_gmt(String date_created_gmt) {
        this.date_created_gmt = date_created_gmt;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getDate_modified_gmt() {
        return date_modified_gmt;
    }

    public void setDate_modified_gmt(String date_modified_gmt) {
        this.date_modified_gmt = date_modified_gmt;
    }

    public String getDiscount_total() {
        return discount_total;
    }

    public void setDiscount_total(String discount_total) {
        this.discount_total = discount_total;
    }

    public String getDiscount_tax() {
        return discount_tax;
    }

    public void setDiscount_tax(String discount_tax) {
        this.discount_tax = discount_tax;
    }

    public String getShipping_tax() {
        return shipping_tax;
    }

    public void setShipping_tax(String shipping_tax) {
        this.shipping_tax = shipping_tax;
    }

    public String getShipping_total() {
        return shipping_total;
    }

    public void setShipping_total(String shipping_total) {
        this.shipping_total = shipping_total;
    }

    public String getCart_tax() {
        return cart_tax;
    }

    public void setCart_tax(String cart_tax) {
        this.cart_tax = cart_tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_tax() {
        return total_tax;
    }

    public void setTotal_tax(String total_tax) {
        this.total_tax = total_tax;
    }

    public String getCustomer_ip_address() {
        return customer_ip_address;
    }

    public void setCustomer_ip_address(String customer_ip_address) {
        this.customer_ip_address = customer_ip_address;
    }

    public String getCustomer_user_agent() {
        return customer_user_agent;
    }

    public void setCustomer_user_agent(String customer_user_agent) {
        this.customer_user_agent = customer_user_agent;
    }

    public String getCustomer_note() {
        return customer_note;
    }

    public void setCustomer_note(String customer_note) {
        this.customer_note = customer_note;
    }

    public String getBilling_fname() {
        return billing_fname;
    }

    public void setBilling_fname(String billing_fname) {
        this.billing_fname = billing_fname;
    }

    public String getBilling_last_name() {
        return billing_last_name;
    }

    public void setBilling_last_name(String billing_last_name) {
        this.billing_last_name = billing_last_name;
    }

    public String getBilling_company() {
        return billing_company;
    }

    public void setBilling_company(String billing_company) {
        this.billing_company = billing_company;
    }

    public String getBilling_address_1() {
        return billing_address_1;
    }

    public void setBilling_address_1(String billing_address_1) {
        this.billing_address_1 = billing_address_1;
    }

    public String getBilling_adddress_2() {
        return billing_adddress_2;
    }

    public void setBilling_adddress_2(String billing_adddress_2) {
        this.billing_adddress_2 = billing_adddress_2;
    }

    public String getBilling_city() {
        return billing_city;
    }

    public void setBilling_city(String billing_city) {
        this.billing_city = billing_city;
    }

    public String getBilling_state() {
        return billing_state;
    }

    public void setBilling_state(String billing_state) {
        this.billing_state = billing_state;
    }

    public String getBilling_postcode() {
        return billing_postcode;
    }

    public void setBilling_postcode(String billing_postcode) {
        this.billing_postcode = billing_postcode;
    }

    public String getBilling_country() {
        return billing_country;
    }

    public void setBilling_country(String billing_country) {
        this.billing_country = billing_country;
    }

    public String getBilling_email() {
        return billing_email;
    }

    public void setBilling_email(String billing_email) {
        this.billing_email = billing_email;
    }

    public String getBilling_phone() {
        return billing_phone;
    }

    public void setBilling_phone(String billing_phone) {
        this.billing_phone = billing_phone;
    }

    public String getShipping_fname() {
        return shipping_fname;
    }

    public void setShipping_fname(String shipping_fname) {
        this.shipping_fname = shipping_fname;
    }

    public String getShipping_last_name() {
        return shipping_last_name;
    }

    public void setShipping_last_name(String shipping_last_name) {
        this.shipping_last_name = shipping_last_name;
    }

    public String getShipping_company() {
        return shipping_company;
    }

    public void setShipping_company(String shipping_company) {
        this.shipping_company = shipping_company;
    }

    public String getShippingbilling_address_1() {
        return shippingbilling_address_1;
    }

    public void setShippingbilling_address_1(String shippingbilling_address_1) {
        this.shippingbilling_address_1 = shippingbilling_address_1;
    }

    public String getShipping_adddress_2() {
        return shipping_adddress_2;
    }

    public void setShipping_adddress_2(String shipping_adddress_2) {
        this.shipping_adddress_2 = shipping_adddress_2;
    }

    public String getShipping_city() {
        return shipping_city;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public String getShipping_state() {
        return shipping_state;
    }

    public void setShipping_state(String shipping_state) {
        this.shipping_state = shipping_state;
    }

    public String getShipping_postcode() {
        return shipping_postcode;
    }

    public void setShipping_postcode(String shipping_postcode) {
        this.shipping_postcode = shipping_postcode;
    }

    public String getShipping_country() {
        return shipping_country;
    }

    public void setShipping_country(String shipping_country) {
        this.shipping_country = shipping_country;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPayment_method_title() {
        return payment_method_title;
    }

    public void setPayment_method_title(String payment_method_title) {
        this.payment_method_title = payment_method_title;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getCart_hash() {
        return cart_hash;
    }

    public void setCart_hash(String cart_hash) {
        this.cart_hash = cart_hash;
    }

    public String getMeta_data_key() {
        return meta_data_key;
    }

    public void setMeta_data_key(String meta_data_key) {
        this.meta_data_key = meta_data_key;
    }

    public String getMeta_data_value() {
        return meta_data_value;
    }

    public void setMeta_data_value(String meta_data_value) {
        this.meta_data_value = meta_data_value;
    }

    public String getMeta_data_value_formatted_number() {
        return meta_data_value_formatted_number;
    }

    public void setMeta_data_value_formatted_number(String meta_data_value_formatted_number) {
        this.meta_data_value_formatted_number = meta_data_value_formatted_number;
    }

    public String getMeta_data_value_document_type() {
        return meta_data_value_document_type;
    }

    public void setMeta_data_value_document_type(String meta_data_value_document_type) {
        this.meta_data_value_document_type = meta_data_value_document_type;
    }

    public String getLine_item_name() {
        return line_item_name;
    }

    public void setLine_item_name(String line_item_name) {
        this.line_item_name = line_item_name;
    }

    public String getLine_item_tax_class() {
        return line_item_tax_class;
    }

    public void setLine_item_tax_class(String line_item_tax_class) {
        this.line_item_tax_class = line_item_tax_class;
    }

    public String getLine_item_subtotal() {
        return line_item_subtotal;
    }

    public void setLine_item_subtotal(String line_item_subtotal) {
        this.line_item_subtotal = line_item_subtotal;
    }

    public String getLine_item_subtotal_tax() {
        return line_item_subtotal_tax;
    }

    public void setLine_item_subtotal_tax(String line_item_subtotal_tax) {
        this.line_item_subtotal_tax = line_item_subtotal_tax;
    }

    public String getLine_item_total() {
        return line_item_total;
    }

    public void setLine_item_total(String line_item_total) {
        this.line_item_total = line_item_total;
    }

    public String getLine_item_total_tax() {
        return line_item_total_tax;
    }

    public void setLine_item_total_tax(String line_item_total_tax) {
        this.line_item_total_tax = line_item_total_tax;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getShipping_method_title() {
        return shipping_method_title;
    }

    public void setShipping_method_title(String shipping_method_title) {
        this.shipping_method_title = shipping_method_title;
    }

    public String getShipping_method_id() {
        return shipping_method_id;
    }

    public void setShipping_method_id(String shipping_method_id) {
        this.shipping_method_id = shipping_method_id;
    }

    public String getShipping_line_total() {
        return shipping_line_total;
    }

    public void setShipping_line_total(String shipping_line_total) {
        this.shipping_line_total = shipping_line_total;
    }

    public String getShipping_total_taxes() {
        return shipping_total_taxes;
    }

    public void setShipping_total_taxes(String shipping_total_taxes) {
        this.shipping_total_taxes = shipping_total_taxes;
    }

    public String getShipping_meta_data_key() {
        return shipping_meta_data_key;
    }

    public void setShipping_meta_data_key(String shipping_meta_data_key) {
        this.shipping_meta_data_key = shipping_meta_data_key;
    }

    public String getShipping_meta_data_value() {
        return shipping_meta_data_value;
    }

    public void setShipping_meta_data_value(String shipping_meta_data_value) {
        this.shipping_meta_data_value = shipping_meta_data_value;
    }

    public String getLinks_self_href() {
        return links_self_href;
    }

    public void setLinks_self_href(String links_self_href) {
        this.links_self_href = links_self_href;
    }

    public String getLinks_collection_href() {
        return links_collection_href;
    }

    public void setLinks_collection_href(String links_collection_href) {
        this.links_collection_href = links_collection_href;
    }

    public String getLinks_customer_href() {
        return links_customer_href;
    }

    public void setLinks_customer_href(String links_customer_href) {
        this.links_customer_href = links_customer_href;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getMeta_id() {
        return meta_id;
    }

    public void setMeta_id(Integer meta_id) {
        this.meta_id = meta_id;
    }

    public Integer getLine_id() {
        return line_id;
    }

    public void setLine_id(Integer line_id) {
        this.line_id = line_id;
    }

    public Integer getLine_quantity() {
        return line_quantity;
    }

    public void setLine_quantity(Integer line_quantity) {
        this.line_quantity = line_quantity;
    }

    public Integer getLine_product_id() {
        return line_product_id;
    }

    public void setLine_product_id(Integer line_product_id) {
        this.line_product_id = line_product_id;
    }

    public Integer getLine_variation_id() {
        return line_variation_id;
    }

    public void setLine_variation_id(Integer line_variation_id) {
        this.line_variation_id = line_variation_id;
    }

    public Integer getLine_price() {
        return line_price;
    }

    public void setLine_price(Integer line_price) {
        this.line_price = line_price;
    }

    public Integer getShipping_lines_id() {
        return shipping_lines_id;
    }

    public void setShipping_lines_id(Integer shipping_lines_id) {
        this.shipping_lines_id = shipping_lines_id;
    }

    public Integer getShipping_meta_data_id() {
        return shipping_meta_data_id;
    }

    public void setShipping_meta_data_id(Integer shipping_meta_data_id) {
        this.shipping_meta_data_id = shipping_meta_data_id;
    }

    public Integer getMeta_data_value_number() {
        return meta_data_value_number;
    }

    public void setMeta_data_value_number(Integer meta_data_value_number) {
        this.meta_data_value_number = meta_data_value_number;
    }

    public Integer getMeta_data_value_order_id() {
        return meta_data_value_order_id;
    }

    public void setMeta_data_value_order_id(Integer meta_data_value_order_id) {
        this.meta_data_value_order_id = meta_data_value_order_id;
    }
}