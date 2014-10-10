/* Copyright (C) 2014 konik.io
 *
 * This file is part of the Konik library.
 *
 * The Konik library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * The Konik library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with the Konik library. If not, see <http://www.gnu.org/licenses/>.
 */
package io.konik.zugferd.entity;

import io.konik.builder.GenerateBuilder;
import io.konik.jaxb.adapter.ParameterProfileAdapter;
import io.konik.validator.annotation.Basic;
import io.konik.validator.annotation.Extended;
import io.konik.zugferd.profile.ConformanceLevel;
import io.konik.zugferd.profile.Profile;
import io.konik.zugferd.unqualified.Indicator;

import java.beans.ConstructorProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * = The Exchanged Document Context
 * 
 * Grouping of the properties of the message.
 */
@GenerateBuilder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExchangedDocumentContextType", propOrder = { "test", "businessProcess", "guideline" })
public class Context {

   @XmlElement(name = "TestIndicator")
   private Indicator test;

   @Valid
   @XmlElement(name = "BusinessProcessSpecifiedDocumentContextParameter")
   private Parameter businessProcess;

   @NotNull
   @Valid
   @XmlElement(name = "GuidelineSpecifiedDocumentContextParameter", required = true)
   @XmlJavaTypeAdapter(ParameterProfileAdapter.class)
   private Profile guideline;

   /**
    * Instantiates a new context.
    */
   public Context() {
   }

   /**
    * Instantiates a new context with a latest version profile.
    *
    * @param conformanceLevel the profile
    */
   @ConstructorProperties(value = { "conformanceLevel" })
   public Context(ConformanceLevel conformanceLevel) {
      this.guideline = new Profile(conformanceLevel);
   }

   /**
    * The test indicator.
    * 
    * Flags the invoice such that it should not be processed in the target system.
    * 
    * The sales tax liability does not arise. This flag is used in particular in the introductory period of new business
    * relationships.
    * 
    * Profile:: BASIC
    * 
    * Default:: +false+
    * 
    * @return true if this invoice is for testing purpose only
    */
   @Basic
   public boolean isTest() {
      if (test == null) { return false; }
      return test.getIndicator();
   }

   /**
    * The test indicator.
    * 
    * Flags the invoice such that it should not be processed in the target system.
    * 
    * Profile:: BASIC
    * 
    * Default:: +false+
    */
   public void setTest() {
      this.test = Indicator.trueIndicator();
      
   }

   /**
    * The test indicator.
    * 
    * Flags the invoice such that it should not be processed in the target system.
    * 
    * The sales tax liability does not arise. This flag is used in particular in the introductory period of new business
    * relationships.
    * 
    * Profile:: BASIC
    * 
    * Default:: +false+
    */
   public void setNotTest() {
      this.test = Indicator.falseIndicator();
      
   }

   /**
    * Gets the business process.
    *
    * Profile:: EXTENDED
    *
    * Example:: production Materials, other Materials, freight Invoices
    *
    * @return the business process
    */
   @Extended
   public String getBusinessProcess() {
      return businessProcess == null ? null : businessProcess.getId();
   }

   /**
    * Sets the business process.
    * 
    * Profile:: EXTENDED
    * 
    * Example:: production Materials, other Materials, freight Invoices
    * 
    * @param businessProcess the new business process
    */
   public void setBusinessProcess(String businessProcess) {
      this.businessProcess = new Parameter(businessProcess);
      
   }

   /**
    * Gets the guideline or profile of the invoice
    * 
    * Profile:: BASIC
    * 
    * Example:: +urn:ferd:CrossIndustryDocument:invoice:1p0:extended+
    * 
    * @return the profile
    */
   @Basic
   public Profile getGuideline() {
      return this.guideline;
   }

   /**
    * Sets the guideline or profile of the invoice
    * 
    * Profile:: BASIC
    * 
    * Example:: +urn:ferd:CrossIndustryDocument:invoice:1p0:extended+
    *
    * @param guideline the new profile
    * @return the context
    */
   public void setGuideline(Profile guideline) {
      this.guideline = guideline;
//      
   }
}
