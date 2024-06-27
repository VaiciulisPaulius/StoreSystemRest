package lt.viko.eif.pvaiciulis.StoreSystemRest.db;

import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.PersonRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.ReceiptRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.discount.DiscountCardRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.discount.DiscountRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.product.EntityProductRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.db.repositories.product.QuantifiableProductRepository;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.Person;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.Receipt;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.Discount;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.DiscountCard;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.discount.DiscountCardCategory;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.product.AmountType;
import lt.viko.eif.pvaiciulis.StoreSystemRest.model.product.QuantifiableProduct;
import lt.viko.eif.pvaiciulis.StoreSystemRest.service.DiscountCardService;
import lt.viko.eif.pvaiciulis.StoreSystemRest.service.EntityProductService;
import lt.viko.eif.pvaiciulis.StoreSystemRest.service.ReceiptService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Configuration
class LoadDatabase {

	private boolean loadData = false;
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	private DiscountCardService discountCardService;
	private ReceiptService receiptService;
	private EntityProductService entityProductService;

	@Autowired
	public LoadDatabase(@Lazy DiscountCardService discountCardService, @Lazy ReceiptService receiptService) {
		this.discountCardService = discountCardService;
		this.receiptService = receiptService;
	}

	@Bean
	CommandLineRunner initDatabase(ReceiptRepository receiptRepository, PersonRepository personRepository,
								   EntityProductRepository entityProductRepository, QuantifiableProductRepository quantifiableProductRepository,
								   DiscountRepository discountRepository, DiscountCardRepository discountCardRepository) {

		return args -> {
			if(loadData) {
				saveEntities(personRepository,
						quantifiableProductRepository,
						discountCardRepository,
						discountRepository);

				retrieveAndProcessEntitiesToReceipt(receiptRepository);


				//employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));
			}
		};
	}

	@Transactional
	private void saveEntities(PersonRepository personRepository,
							  QuantifiableProductRepository quantifiableProductRepository,
							  DiscountCardRepository discountCardRepository,
							  DiscountRepository discountRepository) {
		QuantifiableProduct milk = new QuantifiableProduct("Lactose free milk",
				2098765112, 1.99, AmountType.amount, 1);

		QuantifiableProduct steak = new QuantifiableProduct("Meat",
				2098765115, 10.99, AmountType.amount, 1);

		QuantifiableProduct rubiksCube = new QuantifiableProduct("Rubiks cube",
				2098744513, 4.99, AmountType.amount, 1);

		QuantifiableProduct chips = new QuantifiableProduct("Chips",
				2098741285, 4.5, AmountType.amount, 2);

		QuantifiableProduct banana = new QuantifiableProduct("Banana",
				2098744413, 1.2, AmountType.kg, 1.2);

		Person person1 = new Person("Paulius", "Vaiciulis", "+37069744323", Date.valueOf("2002-05-31"));
		DiscountCard discountCard = new DiscountCard(DiscountCardCategory.Member, person1, discountCardService.generateUniqueBarcode());

		Discount steakDiscount = new Discount(7.99, DiscountCardCategory.Member, steak);
		Discount rubiksCubeDiscount = new Discount(DiscountCardCategory.Member, 50, rubiksCube);

		//jdbc:h2:tcp://10.0.30.42:9092/mem:test;DB_CLOSE_DELAY=-1;MODE=MySQL

		quantifiableProductRepository.save(milk);
		quantifiableProductRepository.save(steak);
		quantifiableProductRepository.save(rubiksCube);
		quantifiableProductRepository.save(chips);
		quantifiableProductRepository.save(banana);

		personRepository.save(person1);

		discountCardRepository.save(discountCard);

		discountRepository.save(steakDiscount);
		discountRepository.save(rubiksCubeDiscount);
	}

	@Transactional
	private void retrieveAndProcessEntitiesToReceipt(ReceiptRepository receiptRepository) {
		Receipt receipt = new Receipt();
//		receiptService.scanProduct(receipt, milk.getBarCode());
//		receiptService.scanProduct(receipt, steak.getBarCode());
//		receiptService.scanProduct(receipt, rubiksCube.getBarCode());
//		receiptService.scanProduct(receipt, discountCard.getBarCode());

		//receiptService.scanProduct(receipt,2098765112);
		//receiptService.scanProduct(receipt, 2098765115);
		//receiptService.scanProduct(receipt, 2098744513);
		//receipt.finishTransaction();

		receiptRepository.save(receipt);
	}
}
