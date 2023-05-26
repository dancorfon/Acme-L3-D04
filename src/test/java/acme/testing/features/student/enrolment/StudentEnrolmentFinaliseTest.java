
package acme.testing.features.student.enrolment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.testing.TestHarness;

public class StudentEnrolmentFinaliseTest extends TestHarness {

	// Internal data ----------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepositoryTest repository;

	// Test methods -----------------------------------------------------------


	@ParameterizedTest
	@CsvFileSource(resources = "/features/student/enrolment/finalise-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int enrolmentIndex, final String code, final String creditCardHolder, final String creditCardNumber, final String expiryDate, final String cvc) {
		// HINT: this test authenticates as an student, lists his or her enrolments,
		// HINT: then selects one of them, and finalises it.

		super.signIn("student2", "student2");

		super.clickOnMenu("Student", "My enrolments");
		super.checkListingExists();
		super.checkColumnHasValue(enrolmentIndex, 0, code);

		super.clickOnListingRecord(enrolmentIndex);
		super.checkFormExists();
		super.fillInputBoxIn("ccHolder", creditCardHolder);
		super.fillInputBoxIn("ccNumber", creditCardNumber);
		super.fillInputBoxIn("expiryDate", expiryDate);
		super.fillInputBoxIn("cvc", cvc);
		super.clickOnSubmit("Finalise");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/features/student/enrolment/finalise-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test200Negative(final int enrolmentIndex, final String code, final String creditCardHolder, final String creditCardNumber, final String expiryDate, final String cvc) {
		// HINT: this test attempts to finalise an enrolment without entering the required data

		super.signIn("student2", "student2");

		super.clickOnMenu("Student", "My enrolments");
		super.checkListingExists();
		super.checkColumnHasValue(enrolmentIndex, 0, code);

		super.clickOnListingRecord(enrolmentIndex);
		super.checkFormExists();
		super.fillInputBoxIn("ccHolder", creditCardHolder);
		super.fillInputBoxIn("ccNumber", creditCardNumber);
		super.fillInputBoxIn("expiryDate", expiryDate);
		super.fillInputBoxIn("cvc", cvc);
		super.clickOnSubmit("Finalise");
		super.checkErrorsExist();

		super.signOut();
	}

	//	@Test
	//	public void test300Hacking() {
	//		// HINT: this test tries to finalise an enrolment with a role other than "Student".
	//
	//		Collection<Enrolment> enrolments;
	//		String params;
	//
	//		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student1");
	//		for (final Enrolment enrolment : enrolments)
	//			if (enrolment.isDraftMode()) {
	//				params = String.format("id=%d", enrolment.getId());
	//
	//				super.checkLinkExists("Sign in");
	//				super.request("/student/enrolment/finalise", params);
	//				super.checkPanicExists();
	//
	//				super.signIn("administrator", "administrator");
	//				super.request("/student/enrolment/finalise", params);
	//				super.checkPanicExists();
	//				super.signOut();
	//
	//				super.signIn("auditor2", "auditor2");
	//				super.request("/student/enrolment/finalise", params);
	//				super.checkPanicExists();
	//				super.signOut();
	//			}
	//	}
	//
	//	@Test
	//	public void test301Hacking() {
	//		// HINT: this test tries to finalise a finalised enrolment that was registered by the principal.
	//
	//		Collection<Enrolment> enrolments;
	//		String params;
	//
	//		super.signIn("student1", "student1");
	//		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student1");
	//		for (final Enrolment enrolment : enrolments)
	//			if (!enrolment.isDraftMode()) {
	//				params = String.format("id=%d", enrolment.getId());
	//				super.request("/employer/job/finalise", params);
	//				super.checkPanicExists();
	//			}
	//		super.signOut();
	//	}
	//
	//	@Test
	//	public void test302Hacking() {
	//		// HINT: this test tries to finalise an enrolment that wasn't registered by the principal,
	//		// HINT+ be it finalised or not.
	//
	//		Collection<Enrolment> enrolments;
	//		String params;
	//
	//		super.signIn("student2", "student2");
	//		enrolments = this.repository.findManyEnrolmentsByStudentUsername("student1");
	//		for (final Enrolment enrolment : enrolments) {
	//			params = String.format("id=%d", enrolment.getId());
	//			super.request("/employer/job/finalise", params);
	//			super.checkPanicExists();
	//		}
	//		super.signOut();
	//	}

}
