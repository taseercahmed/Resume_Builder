package com.semixtech.cv_resume_builder.home.templates

import android.util.Log
import com.semixtech.cv_resume_builder.helper.TemplateDefaultModel


class Template1() {

    lateinit var templateDefaultModel: TemplateDefaultModel

    fun convert(templateDefaultModel: TemplateDefaultModel): String {
        this@Template1.templateDefaultModel = templateDefaultModel
        Log.e("convert", "data --->  Template 1 ---> ${templateDefaultModel.toString()}")

        return getfinalHTML()
    }

    private fun getfinalHTML(): String {

        return getHeader() + getStyleWithHtmlBody()
    }

    private fun getHeader(): String {

        return "<!DOCTYPE html><html><head><link " +
                "rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" /><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"/><link rel=\"preconnect\" href=\"https://fonts.googleapis.com\" /><link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin /></head>"
    }

    private fun getStyleWithHtmlBody(): String {
        var html =
            "<style> body {margin: 0 auto;padding: 20px;font-family: " +
                    "${this@Template1.templateDefaultModel.fontfamily} !important;padding-left: 35px;" +
                    "padding-right: 35px;word-break: break-word;}* {box-sizing: border-box;}.icon-size " +
                    "{font-size: 24px !important;}.no-pm {padding: 0;margin: 0;}.main {padding: 8px 18px;}." +
                    "pdf-1 {padding: 5px 18px;}.main-div {background-color: " +
                    "${this@Template1.templateDefaultModel.color};padding: 10px 18px 10px 18px;margin-top: 10px;}" +
                    ".total-due {font-size: 22px;color: #707070;text-align: center;}.invoice-to " +
                    "{margin: 10px 0 0 0;font-size: 28px;font-weight: 600;color: " +
                    "${this@Template1.templateDefaultModel.color};}.owner-name" +
                    " {margin: 0 0px 4px 0;font-weight: 600;font-size: 26px;}.client-name " +
                    "{margin: 0 0 4px 0;font-size: 26px;font-weight: 600;color: black;}.vat" +
                    " {font-size: 20px;color: black;font-weight: 600;}.vat-no {color: black;font-weight: 400 " +
                    "!important;}.unpaid {margin: 5px 0 5px 0;text-align: left;color: #707070;}.text-xs " +
                    "{margin: 1px 0 1px 0;font-size: 20px;color: black;}.text-xs-black" +
                    " {margin: 5px 0 0px 0;font-size: 20px;color: black;font-weight: 500;}.icons-left " +
                    "{padding: 5px 10px 5px 0px;}.icons-left-add {padding: 5px 15px 5px 0px;}.fa" +
                    " {margin: 5px 0 0;color: ${this@Template1.templateDefaultModel.color};font-size: 25px;}" +
                    "table {margin-top: 1rem;margin-bottom: 1rem;width: 100%;}.table-padding {padding: 0 18px;}table thead th,table td,table th {text-align: center;border: 1px solid #acacac;}th {text-align: center;padding: 10px 0;font-size: 21px;font-weight: bold;}td {border: 1px solid #e0e0e0;text-align: center;padding: 10px 0;font-size: 20px;font-weight: 400;color: #3a3938;}.paragraph {margin: 5px 0 0;font-size: 20px;line-height: 1.5;text-align: justify;color: #3a3938;}.box-light {margin: 0px 0px 8px 0;padding: 14px 20.7px 15px 20px;background-color: ${this@Template1.templateDefaultModel.color};}.unpaid-bg {width: 75px;margin: 2px 0px 6px -16px;padding: 5px 0 4px;background-color: ${this@Template1.templateDefaultModel.color};font-size: 20px;font-weight: 500;text-align: center;color: red;}.paid-bg {width: 75px;margin: 2px 0px 6px -16px;padding: 5px 0 4px;text-align: center;background-color: #d9efda;font-size: 20px;font-weight: 500;color: #21a826;}.text-sm {font-size: 20px;font-weight: 400;text-align: left;color: black;line-height: 25px;word-break: break-word;}.text-md {font-size: 23px;font-weight: 400;text-align: left;color: black;line-height: 25px;word-break: break-word;}.text-sm-light {font-size: 20px;font-weight: 400;color: #7e7b78;line-height: 28px;word-break: break-word;}.text-sm-orange {font-size: 20px;font-weight: 400;color: ${this@Template1.templateDefaultModel.color};line-height: 28px;}.item-image {width: 100px;height: 95px;float: left;margin-right: 16px;}.image-title {font-weight: 400;color: black;font-size: 23px;word-break: break-word;}.invoiceNo {margin: 0 0 7px 0;font-weight: 400;font-size: 32px;color: #3a3938;}.text-lg-orange {font-size: 24px;font-weight: 500;color: ${this@Template1.templateDefaultModel.color};}.border-center {border-left: solid 1px #ddd;}.attached-files {padding: 5px 0 0 0;}.logo-image {width: 100%;height: 100%; margin-bottom: 5px;object-fit: contain;}.logo-image-circle {width: 100%;height: 100%; margin-bottom: 10px;border-radius: 50%;object-fit: contain;}.logo-image-rounded-corner {width: 100%;height: 100%; margin-bottom: 10px;border-radius: 10%;object-fit: contain;}.sign {text-align: center;margin-top: 25px;font-size: 24px;font-weight: 100 !important;}hr {margin-top: 0.2rem;margin-bottom: 0.2rem;}.p-bottom {padding: 0 0 30px 0;}.mt {margin-top: 5px;}.footer-text {font-size: 22px;font-weight: 600;color: black;line-height: 20px;}.Footer {width: 100%;padding: 10px 0 10px 0;margin: 0;bottom: 0;text-align: center;}.logo-div {width: 175px;height: 175px;/* min-height: 95px; */text-align: top;}.p-div2 {padding: 66px 0 0 0;}.main-body {padding-top: 20px;}.bold {font-weight: bold;}.text-wrap {word-wrap: break-word;}.image-fixed {width: 100%;height: 100%;object-fit: contain;}</style>"




        html += "<div class=\"row\"> <div class=\"col-sm-6 dynamic\">"
//        if(this@Template1.templateDefaultModel.isInvoice) {
//            html += "<div class=\"text-md text-right bold\">Status:</div>"
//        }
        html += "</div>"
//        if(this@Template1.templateDefaultModel.isInvoice) {
//            html += "<div class=\"col-sm-6 dynamic\"> <div class=\"text-md text-right\">${this@Template1.templateDefaultModel.paid_status}</div> </div>"
//        }
        html += "</div> </div> </div> </div> </div> </div> <div class=\"table-padding\"> <table> <thead> <tr> <th scope=\"col\" style=\"width: 8%\">SL.</th> <th scope=\"col\" style=\"width: 30%\">Item Description</th> <th scope=\"col\" style=\"width: 20%\">Price</th> <th scope=\"col\" style=\"width: 10%\">Qty</th> <th scope=\"col\" style=\"width: 12%\">Discount</th> <th scope=\"col\" style=\"width: 20%\">Total</th> </tr> </thead> <tbody>"
//        if (this@Template1.templateDefaultModel.items.isNotEmpty()) {
//            var serial = 0
//            for (item in this@Template1.templateDefaultModel.items) {
//                serial++
//                html += "<tr> <td scope=\"row\">${serial}</td> <td>${item.description}</td> <td>${this@Template1.templateDefaultModel.currencySign} ${item.unit_cost}</td> <td>${item.quantity}</td> <td>${this@Template1.templateDefaultModel.currencySign} ${item.discount_amount}</td> <td>${this@Template1.templateDefaultModel.currencySign} ${item.total}</td> </tr>"
//            }
//        }
//        else {
//            html += "<tr> <td scope=\"row\"></td> <td> </td> <td> </td> <td> </td> <td> </td> <td> </td> </tr>"
//        }
        html += "</tbody> </table> </div> <div class=\"main pr-0\"> <div class=\"row no-pm\"> <div class=\"col-sm-6 no-pm\">"

        html += "<div class=\"col-sm-12 no-pm text-right p-bottom\"> <div class=\"row no-pm\"> <div class=\"col-sm-12 no-pm\"> <div> <div class=\"row no-pm\">"


        html += "</div> <div class=\"col-sm-12 no-pm\"> <div class=\"row no-pm\">"




        html += "</div> </div> </div> </div> <div class=\"Footer\"> <div class=\"footer-text\">Thank you for your Business</div> </div>"


        html += "</div> </div> </body> </html>"

        return html
    }

}