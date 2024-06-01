package com.nikola.spring.converter;

import com.nikola.spring.dto.*;
import com.nikola.spring.entities.*;
import com.nikola.spring.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class tempConverter {
    private DecimalFormat decForm = new DecimalFormat("0.00");

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private ModelMapper mapper;

    @Autowired private BookRepository bookRepository; //
    @Autowired private PublisherRepository publisherRepository; //
    @Autowired private AuthorRepository authorRepository; //
    @Autowired private GenreRepository genreRepository; //
    @Autowired private ReviewRepository reviewRepository; //
    @Autowired private AwardRepository awardRepository; //
    @Autowired private WishlistRepository wishlistRepository; //
    @Autowired private BookImageRepository bookImageRepository; //
    @Autowired private CartRepository cartRepository; //
    @Autowired private CartItemRepository cartItemRepository; //
    @Autowired private CustomerRepository customerRepository;
    @Autowired private LoyaltyCardRepository loyaltyCardRepository; //
    @Autowired private OrderRepository orderRepository; //
    @Autowired private OrderItemRepository orderItemRepository; //
    @Autowired private OrderAddressRepository orderAddressRepository; //
    @Autowired private PersistanceLoginRepository persistanceLoginRepository; //
    @Autowired private RequestLoyaltyCardRepository requestLoyaltyCardRepository; //
    @Autowired private RoleRepository roleRepository; //
    @Autowired private ShippingAddressRepository shippingAddressRepository; //
    @Autowired private UserRepository userRepository; //


    public BookDto entityToDto(BookEntity bookEntity){
        BookDto returnValue = mapper.map(bookEntity, BookDto.class);

        Optional<AuthorEntity> authorEntityOptional = Optional.ofNullable(bookEntity.getAuthor());
        if(authorEntityOptional.isPresent()){
            returnValue.setAuthorId(authorEntityOptional.get().getId());
        }
        Optional<GenreEntity> genreEntityOptional = Optional.ofNullable(bookEntity.getGenre());
        if(genreEntityOptional.isPresent()){
            returnValue.setGenreId(genreEntityOptional.get().getId());
        }
        Optional<PublisherEntity> publisherEntityOptional = Optional.ofNullable(bookEntity.getPublisher());
        if(publisherEntityOptional.isPresent()){
            returnValue.setPublisherId(publisherEntityOptional.get().getId());
        }
        Optional<Byte> byteOptional = Optional.ofNullable(bookEntity.getAvailable());
        if(byteOptional.isPresent()){
            returnValue.setAvailable(byteOptional.get());
        }
        Optional<List<ReviewEntity>> reviewsOptional = Optional.ofNullable(bookEntity.getReviews());
        List<Integer> reviewsIds = new ArrayList<>();
        if(reviewsOptional.isPresent()){
            for(ReviewEntity review:reviewsOptional.get()){
                reviewsIds.add(review.getId());
            }
        }
        returnValue.setReviewsIds(reviewsIds);

        Double price = Double.valueOf(decForm.format(returnValue.getPrice()));
        returnValue.setPrice(price);

        Optional<List<AwardEntity>> optionalAwardEntities = Optional.ofNullable(bookEntity.getAwards());
        List<Integer> awardsIds = new ArrayList<>();
        if(optionalAwardEntities.isPresent()){
            for(AwardEntity award: optionalAwardEntities.get()){
                awardsIds.add(award.getId());
            }
        }
        returnValue.setAwardsIds(awardsIds);

        Optional<List<WishlistEntity>> optionalWishlistEntities = Optional.ofNullable(bookEntity.getWishlists());
        List<Integer> wishlistIds = new ArrayList<>();
        if(optionalWishlistEntities.isPresent()){
            for(WishlistEntity wishlist:optionalWishlistEntities.get()){
                wishlistIds.add(wishlist.getId());
            }
        }
        returnValue.setWishlistsIds(wishlistIds);
        return returnValue;
    }

    public BookEntity dtoToEntity(BookDto bookDto){
        BookEntity returnValue = mapper.map(bookDto,BookEntity.class);
        Optional<Integer> authorIdOptional = Optional.ofNullable(bookDto.getAuthorId());
        if(authorIdOptional.isPresent()){
            Integer authorId = authorIdOptional.get();
            AuthorEntity author = authorRepository.findById(authorId).orElse(null);
            if(author != null){
                returnValue.setAuthor(author);
            }
        }
        Optional<Integer> genreIdOptional = Optional.ofNullable(bookDto.getGenreId());
        if(genreIdOptional.isPresent()){
            Integer genreId= genreIdOptional.get();
            GenreEntity genre = genreRepository.findById(genreId).orElse(null);
            if(genre != null){
                returnValue.setGenre(genre);
            }
        }
        Optional<Integer> publisherIdOptional  = Optional.ofNullable(bookDto.getPublisherId());
        if(publisherIdOptional.isPresent()){
            Integer publisherId = publisherIdOptional.get();
            PublisherEntity publisher = publisherRepository.findById(publisherId).orElse(null);
            if(publisher != null){
                returnValue.setPublisher(publisher);
            }
        }
        Optional<Byte> byteOptional = Optional.ofNullable(bookDto.getAvailable());
        if(byteOptional.isPresent()){
            returnValue.setAvailable(byteOptional.get());
        }
        Optional<List<Integer>> reviewsIdsOptional = Optional.ofNullable(bookDto.getReviewsIds());
        List<ReviewEntity> reviews = new ArrayList<>();
        if(reviewsIdsOptional.isPresent()){
            for(Integer reviewId:reviewsIdsOptional.get()){
                ReviewEntity review = reviewRepository.findById(reviewId).orElse(null);
                if(review != null){
                    reviews.add(review);
                }
            }
        }
        returnValue.setReviews(reviews);

        Optional<List<Integer>> awardsIdsOptional = Optional.ofNullable(bookDto.getAwardsIds());
        List<AwardEntity> awards = new ArrayList<>();
        if(awardsIdsOptional.isPresent()){
            for(Integer awardId: awardsIdsOptional.get()){
                AwardEntity award = awardRepository.findById(awardId).orElse(null);
                if(award != null){
                    awards.add(award);
                }
            }
        }
        returnValue.setAwards(awards);

        Optional<List<Integer>> wishlistIdsOptional = Optional.ofNullable(bookDto.getWishlistsIds());
        List<WishlistEntity> wishlists = new ArrayList<>();
        if(wishlistIdsOptional.isPresent()){
            for(Integer wishlistId: wishlistIdsOptional.get()){
                WishlistEntity wishlist = wishlistRepository.findById(wishlistId).orElse(null);
                if(wishlist != null){
                    wishlists.add(wishlist);
                }
            }
        }
        returnValue.setWishlists(wishlists);
        return returnValue;
    }

    public AuthorEntity dtoToEntity(AuthorDto authorDto){
        AuthorEntity returnValue = mapper.map(authorDto,AuthorEntity.class);

        Optional<List<Integer>> bookIdsOptional = Optional.ofNullable(authorDto.getBooksIds());
        List<BookEntity> books = new ArrayList<>();
        if(bookIdsOptional.isPresent()){
            for(Integer bookId : bookIdsOptional.get()){
                BookEntity book = bookRepository.findById(bookId).orElse(null);
                if(book != null){
                    books.add(book);
                }
            }
        }
        returnValue.setBooks(books);
        return returnValue;
    }
    public AuthorDto entityToDto(AuthorEntity authorEntity){
        AuthorDto returnValue = mapper.map(authorEntity,AuthorDto.class);

        Optional<List<BookEntity>> optionalBookEntities = Optional.ofNullable(authorEntity.getBooks());
        List<Integer> bookIds = new ArrayList<>();
        if(optionalBookEntities.isPresent()){
            for(BookEntity book: optionalBookEntities.get()){
                bookIds.add(book.getId());
            }
        }
        returnValue.setBooksIds(bookIds);
        return returnValue;
    }
    public GenreDto entityToDto(GenreEntity genreEntity){
        GenreDto returnValue = mapper.map(genreEntity,GenreDto.class);
        Optional<List<BookEntity>> optionalBookEntities = Optional.ofNullable(genreEntity.getBooks());
        List<Integer> booksIds = new ArrayList<>();
        if(optionalBookEntities.isPresent()){
            for(BookEntity book: optionalBookEntities.get()){
                booksIds.add(book.getId());
            }
        }
        returnValue.setBooksIds(booksIds);
        return returnValue;
    }
    public GenreEntity dtoToEntity(GenreDto genreDto){
        GenreEntity returnValue = mapper.map(genreDto,GenreEntity.class);
        Optional<List<Integer>> booksIdsOptional = Optional.ofNullable(genreDto.getBooksIds());
        List<BookEntity> books = new ArrayList<>();
        if(booksIdsOptional.isPresent()){
            for(Integer bookId : booksIdsOptional.get()){
                BookEntity book = bookRepository.findById(bookId).orElse(null);
                if(book != null){
                    books.add(book);
                }
            }
        }
        returnValue.setBooks(books);
        return returnValue;
    }

    public PublisherEntity dtoToEntity(PublisherDto publisherDto){
        PublisherEntity returnValue = mapper.map(publisherDto,PublisherEntity.class);
        Optional<List<Integer>> booksIdsOptional = Optional.ofNullable(publisherDto.getBookIds());
        List<BookEntity> books = new ArrayList<>();
        if(booksIdsOptional.isPresent()){
            for(Integer bookId: booksIdsOptional.get()){
                BookEntity book = bookRepository.findById(bookId).orElse(null);
                if(book != null){
                    books.add(book);
                }
            }
        }
        returnValue.setBooks(books);
        return returnValue;
    }
    public PublisherDto entityToDto(PublisherEntity publisherEntity){
        PublisherDto returnValue = mapper.map(publisherEntity,PublisherDto.class);
        Optional<List<BookEntity>> optionalBookEntities = Optional.ofNullable(publisherEntity.getBooks());
        List<Integer> booksIds = new ArrayList<>();
        if(optionalBookEntities.isPresent()){
            for(BookEntity book: optionalBookEntities.get()){
                booksIds.add(book.getId());
            }
        }
        returnValue.setBookIds(booksIds);
        return returnValue;
    }

    public ReviewDto entityToDto (ReviewEntity reviewEntity){
        ReviewDto returnValue = mapper.map(reviewEntity,ReviewDto.class);
        Optional<BookEntity> bookEntityOptional = Optional.ofNullable(reviewEntity.getBook());
        if(bookEntityOptional.isPresent()){
            returnValue.setBookId(bookEntityOptional.get().getId());
        }
        Optional<CustomerEntity> customerEntityOptional = Optional.ofNullable(reviewEntity.getCustomer());
        if(customerEntityOptional.isPresent()){
            returnValue.setCustomerId(customerEntityOptional.get().getId());
        }
        return returnValue;
    }

    public ReviewEntity dtoToEntity(ReviewDto reviewDto){
        ReviewEntity returnValue = mapper.map(reviewDto,ReviewEntity.class);
        Optional<Integer> bookIdOptional = Optional.ofNullable(reviewDto.getBookId());
        if(bookIdOptional.isPresent()){
            BookEntity book = bookRepository.findById(bookIdOptional.get()).orElse(null);
            if(book != null){
                returnValue.setBook(book);
            }
        }
        Optional<Integer> customerIdOptional = Optional.ofNullable(reviewDto.getCustomerId());
        if(customerIdOptional.isPresent()){
            CustomerEntity customer = customerRepository.findById(customerIdOptional.get()).orElse(null);
            if(customer != null){
                returnValue.setCustomer(customer);
            }
        }
        return returnValue;
    }

    public AwardEntity dtoToEntity(AwardDto awardDto){
        AwardEntity returnValue = mapper.map(awardDto,AwardEntity.class);
        Optional<List<Integer>> booksIdsOptional = Optional.ofNullable(awardDto.getBooksIds());
        List<BookEntity> books = new ArrayList<>();
        if(booksIdsOptional.isPresent()){
            for(Integer bookId: booksIdsOptional.get()){
                BookEntity book = bookRepository.findById(bookId).orElse(null);
                if(book != null){
                    books.add(book);
                }
            }
        }
        returnValue.setBooks(books);
        return returnValue;
    }

    public AwardDto entityToDto(AwardEntity awardEntity){
        AwardDto returnValue = mapper.map(awardEntity,AwardDto.class);
        Optional<List<BookEntity>> optionalBookEntities = Optional.ofNullable(awardEntity.getBooks());
        List<Integer> booksIds = new ArrayList<>();
        if(optionalBookEntities.isPresent()){
            for(BookEntity book : optionalBookEntities.get()){
                booksIds.add(book.getId());
            }
        }
        returnValue.setBooksIds(booksIds);
        Optional<LocalDate> localDateOptional = Optional.ofNullable(awardEntity.getDateOfAward());
        if(localDateOptional.isPresent()){
            LocalDate dateOfAward = localDateOptional.get();
            returnValue.setDateOfAward(dateOfAward.toString());
        }
        return returnValue;
    }

    public WishlistDto entityToDto(WishlistEntity wishlistEntity){
        WishlistDto returnValue = mapper.map(wishlistEntity,WishlistDto.class);
        Optional<CustomerEntity> customerEntityOptional = Optional.ofNullable(wishlistEntity.getCustomer());
        if(customerEntityOptional.isPresent()){
            returnValue.setCustomerId(customerEntityOptional.get().getId());
        }
        Optional<List<BookEntity>> optionalBookEntities = Optional.ofNullable(wishlistEntity.getBooks());
        List<Integer> booksIds = new ArrayList<>();
        if(optionalBookEntities.isPresent()){
            for(BookEntity book: optionalBookEntities.get()){
                booksIds.add(book.getId());
            }
        }
        returnValue.setBookIds(booksIds);
        return returnValue;
    }

    public WishlistEntity dtoToEntity(WishlistDto wishlistDto){
        WishlistEntity returnValue = mapper.map(wishlistDto,WishlistEntity.class);
        Optional<Integer> customerIdOptional = Optional.ofNullable(wishlistDto.getCustomerId());
        if(customerIdOptional.isPresent()){
            CustomerEntity customer = customerRepository.findById(customerIdOptional.get()).orElse(null);
            if(customer != null){
                returnValue.setCustomer(customer);
            }
        }
        Optional<List<Integer>> booksIdsOptional = Optional.ofNullable(wishlistDto.getBookIds());
        List<BookEntity> books = new ArrayList<>();
        if(booksIdsOptional.isPresent()){
            for(Integer bookId: booksIdsOptional.get()){
                BookEntity book = bookRepository.findById(bookId).orElse(null);
                if(book != null){
                    books.add(book);
                }
            }
        }
        returnValue.setBooks(books);
        return returnValue;
    }

    public BookImageEntity dtoToEntity(BookImageDto bookImageDto){
        BookImageEntity returnValue = mapper.map(bookImageDto, BookImageEntity.class);
        Optional<Integer> bookIdOptional = Optional.ofNullable(bookImageDto.getBook_id());
        if(bookIdOptional.isPresent()){
            Integer bookId = bookIdOptional.get();
            BookEntity book = bookRepository.findById(bookId).orElse(null);
            if(book != null){
                returnValue.setBook(book);
            }
        }
        return returnValue;
    }

    public BookImageDto entityToDto(BookImageEntity bookImageEntity){
        BookImageDto returnValue = mapper.map(bookImageEntity,BookImageDto.class);
        Optional<BookEntity> bookEntityOptional = Optional.ofNullable(bookImageEntity.getBook());
        if(bookEntityOptional.isPresent()){
            returnValue.setBook_id(bookEntityOptional.get().getId());
        }
        return returnValue;
    }
    public CartEntity dtoToEntity(CartDto cartDto){
        CartEntity returnValue = mapper.map(cartDto,CartEntity.class);

        Optional<Integer> customerIdOptional = Optional.ofNullable(cartDto.getCustomerId());
        if(customerIdOptional.isPresent()){
            returnValue.setCustomer(customerRepository.findById(customerIdOptional.get()).orElse(null));
        }
        Optional<List<Integer>> cartItemsIdsOptional = Optional.ofNullable(cartDto.getCartItemsIds());
        List<CartItemEntity> cartItems = new ArrayList<>();
        if(cartItemsIdsOptional.isPresent()){
            for(Integer cartItemId:cartItemsIdsOptional.get()){
                CartItemEntity item = cartItemRepository.findById(cartItemId).orElse(null);
                if(item != null){
                    cartItems.add(item);
                }
            }
        }
        returnValue.setCartItems(cartItems);
        Double price = Double.valueOf(decForm.format(returnValue.getCartPrice()));
        returnValue.setCartPrice(price);
        return  returnValue;
    }

    public CartDto entityToDto(CartEntity cartEntity){
        CartDto returnValue = mapper.map(cartEntity, CartDto.class);

        Optional<CustomerEntity> customerOptional = Optional.ofNullable(cartEntity.getCustomer());
        if(customerOptional.isPresent()){
            returnValue.setCustomerId(customerOptional.get().getId());
        }
        Optional<List<CartItemEntity>> cartItemsOptional = Optional.ofNullable(cartEntity.getCartItems());
        List<Integer> cartItemsIds = new ArrayList<>();
        if(cartItemsOptional.isPresent()){
            for(CartItemEntity cartItem:cartItemsOptional.get()){
                Integer itemId = cartItem.getId();
                cartItemsIds.add(itemId);
            }
        }
        returnValue.setCartItemsIds(cartItemsIds);
        Double price = Double.valueOf(decForm.format(returnValue.getCartPrice()));
        returnValue.setCartPrice(price);
        return returnValue;
    }

    public CartItemEntity dtoToEntity(CartItemDto cartItemDto){
        CartItemEntity returnValue = mapper.map(cartItemDto,CartItemEntity.class);
        Optional<Integer> cartIdOptional = Optional.ofNullable(cartItemDto.getCartId());
        if (cartIdOptional.isPresent()){
            CartEntity cart = cartRepository.findById(cartIdOptional.get()).orElse(null);
            returnValue.setCart(cart);
        }
        Optional<Integer> bookIdOptional = Optional.ofNullable(cartItemDto.getBookId());
        if(bookIdOptional.isPresent()){
            returnValue.setBook(bookRepository.findById(bookIdOptional.get()).orElse(null));
        }
        return returnValue;
    }

    public CartItemDto entityToDto(CartItemEntity cartItemEntity){
        CartItemDto returnValue = mapper.map(cartItemEntity, CartItemDto.class);
        Optional<CartEntity> cartEntityOptional = Optional.ofNullable(cartItemEntity.getCart());
        if(cartEntityOptional.isPresent()){
            returnValue.setCartId(cartEntityOptional.get().getId());
        }
        Optional<BookEntity> bookEntityOptional = Optional.ofNullable(cartItemEntity.getBook());
        if(bookEntityOptional.isPresent()){
            returnValue.setBookId(bookEntityOptional.get().getId());
        }
        return returnValue;
    }
    public OrderDto entityToDto(OrderEntity orderEntity){
        OrderDto returnValue = mapper.map(orderEntity,OrderDto.class);
        Optional<CartEntity> cartEntityOptional= Optional.ofNullable(orderEntity.getCart());
        if(cartEntityOptional.isPresent()){
            returnValue.setCartId(cartEntityOptional.get().getId());
        }
        Optional<Timestamp> createTimeOptional = Optional.ofNullable(orderEntity.getCreateTime());
        if(createTimeOptional.isPresent()){
            LocalDateTime createTime = createTimeOptional.get().toLocalDateTime();
            String createTimeStr = createTime.format(dateTimeFormatter);
            returnValue.setCreateTime(createTimeStr);
        }
        Optional<OrderAddressEntity> orderAddressEntityOptional = Optional.ofNullable(orderEntity.getOrderAddress());
        if(orderAddressEntityOptional.isPresent()){
            returnValue.setOrderAddressId(orderAddressEntityOptional.get().getId());
        }
        Optional <List<OrderItemEntity>> orderItemsOptional = Optional.ofNullable(orderEntity.getOrderItems());
        List<Integer> orderItemsIds = new ArrayList<>();
        if(orderItemsOptional.isPresent()){
            for(OrderItemEntity orderItem : orderItemsOptional.get()){
                orderItemsIds.add(orderItem.getId());
            }
        }
        returnValue.setOrderItemsIds(orderItemsIds);
        return returnValue;
    }

    public OrderEntity dtoToEntity(OrderDto orderDto){
        OrderEntity returnValue = mapper.map(orderDto,OrderEntity.class);
        Optional<Integer> cartIdOptional = Optional.ofNullable(orderDto.getCartId());
        if(cartIdOptional.isPresent()){
            CartEntity cart = cartRepository.findById(cartIdOptional.get()).orElse(null);
            if(cart != null){
                returnValue.setCart(cart);
            }
        }
        Optional<Integer> orderAddressIdOptional = Optional.ofNullable(orderDto.getOrderAddressId());
        if(orderAddressIdOptional.isPresent()){
            OrderAddressEntity orderAddress = orderAddressRepository.findById(orderAddressIdOptional.get()).orElse(null);
            if(orderAddress != null){
                returnValue.setOrderAddress(orderAddress);
            }
        }
        Optional<List<Integer>> orderItemsIdsOptional = Optional.ofNullable(orderDto.getOrderItemsIds());
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        if(orderItemsIdsOptional.isPresent()){
            for(Integer orderItemId : orderItemsIdsOptional.get()){
                OrderItemEntity orderItem = orderItemRepository.findById(orderItemId).orElse(null);
                if(orderItem != null){
                    orderItemEntities.add(orderItem);
                }
            }
        }
        returnValue.setOrderItems(orderItemEntities);
        return returnValue;
    }
    public OrderItemDto entityToDto(OrderItemEntity orderItemEntity){
        OrderItemDto returnValue = mapper.map(orderItemEntity,OrderItemDto.class);
        Optional<OrderEntity> orderEntityOptional = Optional.ofNullable(orderItemEntity.getOrder());
        if(orderEntityOptional.isPresent()){
            returnValue.setOrderId(orderEntityOptional.get().getId());
        }
        return returnValue;
    }
    public OrderItemEntity dtoToEntity(OrderItemDto orderItemDto){
        OrderItemEntity returnValue = mapper.map(orderItemDto,OrderItemEntity.class);
        Optional<Integer> orderIdOptional = Optional.ofNullable(orderItemDto.getOrderId());
        if(orderIdOptional.isPresent()){
            OrderEntity order = orderRepository.findById(orderIdOptional.get()).orElse(null);
            if(order != null){
                returnValue.setOrder(order);
            }
        }
        return returnValue;
    }

    public OrderAddressDto entityToDto(OrderAddressEntity orderAddressEntity){
        OrderAddressDto returnValue = mapper.map(orderAddressEntity,OrderAddressDto.class);
        Optional<OrderEntity> orderEntityOptional = Optional.ofNullable(orderAddressEntity.getOrderEntity());
        if(orderEntityOptional.isPresent()){
            returnValue.setOrderId(orderEntityOptional.get().getId());
        }
        return returnValue;
    }
    public OrderAddressEntity dtoToEntity(OrderAddressDto orderAddressDto){
        OrderAddressEntity returnValue = mapper.map(orderAddressDto,OrderAddressEntity.class);
        Optional<Integer> orderIdOptional = Optional.ofNullable(orderAddressDto.getOrderId());
        if(orderIdOptional.isPresent()){
            OrderEntity order = orderRepository.findById(orderIdOptional.get()).orElse(null);
            if(order != null){
                returnValue.setOrderEntity(order);
            }
        }
        return returnValue;
    }

    public OrderAddressDto shippingAddressToOrderAddress(ShippingAddressDto shippingAddressDto){
        OrderAddressDto returnValue = mapper.map(shippingAddressDto, OrderAddressDto.class);
        System.out.println(returnValue.toString());

        returnValue.setId(null);
        System.out.println(returnValue.toString());
        return returnValue;
    }

    public OrderItemDto cartItemToOrderItem(CartItemDto cartItemDto){
        OrderItemDto returnValue = new OrderItemDto();
        Optional<Integer> bookIdOptional = Optional.ofNullable(cartItemDto.getBookId());
        if(bookIdOptional.isPresent()){
            Integer bookId = bookIdOptional.get();
            BookEntity book = bookRepository.findById(bookId).orElse(null);
            if(book != null){
                String bookName = book.getTitle();
                Double bookPrice = book.getPrice();
                returnValue.setBookName(bookName);
                returnValue.setBookPrice(bookPrice);
            }
        }
        Optional<Integer> quantityOptional = Optional.ofNullable(cartItemDto.getQuantity());
        if(quantityOptional.isPresent()){
            Integer quantity = quantityOptional.get();
            returnValue.setBookQuantity(quantity);
        }

        Double price = returnValue.getBookPrice()* returnValue.getBookQuantity();
        returnValue.setPrice(price);
        return returnValue;
    }
    public ShippingAddressEntity dtoToEntity(ShippingAddressDto shippingAddressDto){
        ShippingAddressEntity returnValue = mapper.map(shippingAddressDto, ShippingAddressEntity.class);

        Optional<Integer> customerIdOptional = Optional.ofNullable(shippingAddressDto.getCustomerId());
        if(customerIdOptional.isPresent()){
            returnValue.setCustomer(customerRepository.findById(customerIdOptional.get()).orElse(null));
        }
        return  returnValue;
    }

    public  ShippingAddressDto entityToDto(ShippingAddressEntity shippingAddressEntity){
        ShippingAddressDto returnValue = mapper.map(shippingAddressEntity, ShippingAddressDto.class);

        Optional<CustomerEntity> customerOptional = Optional.ofNullable(shippingAddressEntity.getCustomer());
        if(customerOptional.isPresent()){
            returnValue.setCustomerId(customerOptional.get().getId());
        }
        return  returnValue;
    }

    public UserDto entityToDto(UserEntity userEntity){
        UserDto returnValue = mapper.map(userEntity, UserDto.class);

        Optional<List<RoleEntity>> rolesOptional = Optional.ofNullable(userEntity.getRoles());
        List<Integer> rolesIds = new ArrayList<>();
        if(rolesOptional.isPresent()){
            for(RoleEntity roleEntity: rolesOptional.get()){
                rolesIds.add(roleEntity.getId());
            }
        }
        returnValue.setRolesIds(rolesIds);
        Optional<Byte> enabledOptional = Optional.ofNullable(userEntity.getEnabled());
        if(enabledOptional.isPresent()){
            returnValue.setEnabled(enabledOptional.get().shortValue());
            //pretvaranje byta u short zbog baga na model mapperu
        }
        return returnValue;
    }
    public UserEntity dtoToEntity(UserDto userDto){
        UserEntity returnValue = mapper.map(userDto, UserEntity.class);
        Optional<Short> enabledOptional = Optional.ofNullable(userDto.getEnabled());
        if(enabledOptional.isPresent()){
            returnValue.setEnabled(enabledOptional.get().byteValue());
        }
        Optional<List<Integer>> rolesIdsOptional = Optional.ofNullable(userDto.getRolesIds());
        List<RoleEntity> roleEntities = new ArrayList<>();
        if(rolesIdsOptional.isPresent()){
            for(Integer roleId:rolesIdsOptional.get()){
                RoleEntity role = roleRepository.findById(roleId).orElse(null);
                if(role != null){
                    roleEntities.add(role);
                }
            }
        }
        returnValue.setRoles(roleEntities);
        return returnValue;
    }

    public RoleEntity dtoToEntity(RoleDto roleDto) {
        RoleEntity returnValue = mapper.map(roleDto, RoleEntity.class);
        Optional<List<Integer>> userIdsOptional = Optional.of(roleDto.getUsersIds());
        List<UserEntity> userEntities = new ArrayList<>();
        if (userIdsOptional.isPresent()) {
            for (Integer userId : userIdsOptional.get()) {
                UserEntity user = userRepository.findById(userId).orElse(null);
                if (user != null) {
                    userEntities.add(user);
                }
            }
        }
        returnValue.setUsers(userEntities);
        return returnValue;
    }

    public RoleDto entityToDto(RoleEntity roleEntity){
        RoleDto returnValue = mapper.map(roleEntity,RoleDto.class);

        Optional<List<UserEntity>> userEntitiesOptional = Optional.ofNullable(roleEntity.getUsers());
        List<Integer> userIds = new ArrayList<>();
        if(userEntitiesOptional.isPresent()){
            for(UserEntity user:userEntitiesOptional.get()){
                userIds.add(user.getId());
            }
        }
        returnValue.setUsersIds(userIds);
        return returnValue;
    }

    public LoyaltyCardEntity dtoToEntity(LoyaltyCardDto loyaltyCardDto){
        LoyaltyCardEntity returnValue = mapper.map(loyaltyCardDto,LoyaltyCardEntity.class);
        Optional<Integer> customerIdOptional = Optional.ofNullable(loyaltyCardDto.getCustomerId());
        if(customerIdOptional.isPresent()){
            CustomerEntity customer = customerRepository.findById(customerIdOptional.get()).orElse(null);
            if(customer!= null){
                returnValue.setCustomer(customer);
            }
        }
        return returnValue;
    }

    public LoyaltyCardDto entityToDto(LoyaltyCardEntity loyaltyCardEntity){
        LoyaltyCardDto returnValue = mapper.map(loyaltyCardEntity,LoyaltyCardDto.class);
        Optional<CustomerEntity> customerEntityOptional = Optional.ofNullable(loyaltyCardEntity.getCustomer());
        if(customerEntityOptional.isPresent()){
            returnValue.setCustomerId(customerEntityOptional.get().getId());
        }
        return returnValue;
    }

    public PersistanceLoginEntity dtoToEntity(PersistanceLoginDto persistanceLoginDto){
        PersistanceLoginEntity returnValue = mapper.map(persistanceLoginDto,PersistanceLoginEntity.class);
        Optional<Integer> customerIdOptional = Optional.ofNullable(persistanceLoginDto.getCustomerId());
        if(customerIdOptional.isPresent()){
            CustomerEntity customer = customerRepository.findById(customerIdOptional.get()).orElse(null);
            if(customer != null){
                returnValue.setCustomer(customer);
            }
        }
        return returnValue;
    }

    public PersistanceLoginDto entityToDto(PersistanceLoginEntity persistanceLoginEntity){
        PersistanceLoginDto returnValue = mapper.map(persistanceLoginEntity,PersistanceLoginDto.class);
        Optional<CustomerEntity> customerEntityOptional=Optional.ofNullable(persistanceLoginEntity.getCustomer());
        if(customerEntityOptional.isPresent()){
            returnValue.setCustomerId(customerEntityOptional.get().getId());
        }
        return returnValue;
    }

    public RequestLoyaltyCardEntity dtoToEntity(RequestLoyaltyCardDto requestLoyaltyCardDto){
        RequestLoyaltyCardEntity returnValue = mapper.map(requestLoyaltyCardDto,RequestLoyaltyCardEntity.class);
        Optional<Integer> customerIdOptional = Optional.ofNullable(requestLoyaltyCardDto.getCustomerId());
        if(customerIdOptional.isPresent()){
            CustomerEntity customer = customerRepository.findById(customerIdOptional.get()).orElse(null);
            if(customer != null){
                returnValue.setCustomer(customer);
            }
        }
        return returnValue;
    }
    public RequestLoyaltyCardDto entityToDto(RequestLoyaltyCardEntity requestLoyaltyCardEntity){
        RequestLoyaltyCardDto returnValue = mapper.map(requestLoyaltyCardEntity,RequestLoyaltyCardDto.class);
        Optional<CustomerEntity> customerEntityOptional = Optional.ofNullable(requestLoyaltyCardEntity.getCustomer());
        if(customerEntityOptional.isPresent()){
            returnValue.setCustomerId(customerEntityOptional.get().getId());
        }
        return returnValue;
    }

    public CustomerDto entityToDto(CustomerEntity customerEntity){
        CustomerDto returnValue = mapper.map(customerEntity,CustomerDto.class);
        Optional<ShippingAddressEntity> shippingAddressEntityOptional = Optional.ofNullable(customerEntity.getShippingAddress());
        if(shippingAddressEntityOptional.isPresent()){
            returnValue.setShippingAddressId(shippingAddressEntityOptional.get().getId());
        }
        Optional<UserEntity> userEntityOptional = Optional.ofNullable(customerEntity.getUser());
        if(userEntityOptional.isPresent()){
            returnValue.setUserId(userEntityOptional.get().getId());
        }
        Optional<CartEntity> cartEntityOptional = Optional.ofNullable(customerEntity.getCart());
        if(cartEntityOptional.isPresent()){
            returnValue.setCartId(cartEntityOptional.get().getId());
        }
        Optional<LoyaltyCardEntity> loyaltyCardEntityOptional = Optional.ofNullable(customerEntity.getLoyaltyCardEntity());
        if(loyaltyCardEntityOptional.isPresent()){
            returnValue.setLoyaltyCardId(loyaltyCardEntityOptional.get().getId());
        }
        Optional<WishlistEntity> wishlistEntityOptional = Optional.ofNullable(customerEntity.getWishlist());
        if(wishlistEntityOptional.isPresent()){
            returnValue.setWishlistId(wishlistEntityOptional.get().getId());
        }
        Optional<List<PersistanceLoginEntity>> optionalPersistanceLoginEntities = Optional.ofNullable(customerEntity.getPersistanceLogin());
        List<Integer> persistanceLoginsIds = new ArrayList<>();
        if(optionalPersistanceLoginEntities.isPresent()){
            for(PersistanceLoginEntity persistanceLogin:optionalPersistanceLoginEntities.get()){
                persistanceLoginsIds.add(persistanceLogin.getId());
            }
        }
        returnValue.setPersistanceLoginsIds(persistanceLoginsIds);
        return returnValue;
    }

    public CustomerEntity dtoToEntity(CustomerDto customerDto){
        CustomerEntity returnValue = mapper.map(customerDto,CustomerEntity.class);
        Optional<Integer> shippingAddressIdOptional = Optional.ofNullable(customerDto.getShippingAddressId());
        if(shippingAddressIdOptional.isPresent()){
            ShippingAddressEntity shippingAddress = shippingAddressRepository.findById(shippingAddressIdOptional.get()).orElse(null);
            if(shippingAddress != null){
                returnValue.setShippingAddress(shippingAddress);
            }
        }
        Optional<Integer> userIdOptional = Optional.ofNullable(customerDto.getUserId());
        if(userIdOptional.isPresent()){
            UserEntity user = userRepository.findById(userIdOptional.get()).orElse(null);
            if(user != null){
                returnValue.setUser(user);
            }
        }
        Optional<Integer> cartIdOptional = Optional.ofNullable(customerDto.getCartId());
        if(cartIdOptional.isPresent()){
            CartEntity cart = cartRepository.findById(cartIdOptional.get()).orElse(null);
            if(cart != null){
                returnValue.setCart(cart);
            }
        }
        Optional<Integer> loyaltyCardIdOptional = Optional.ofNullable(customerDto.getLoyaltyCardId());
        if(loyaltyCardIdOptional.isPresent()){
            LoyaltyCardEntity loyaltyCard = loyaltyCardRepository.findById(loyaltyCardIdOptional.get()).orElse(null);
            if(loyaltyCard != null){
                returnValue.setLoyaltyCardEntity(loyaltyCard);
            }
        }
        Optional<List<Integer>> persistanceLoginsIds = Optional.ofNullable(customerDto.getPersistanceLoginsIds());
        List<PersistanceLoginEntity> persistanceLoginsEntities = new ArrayList<>();
        if(persistanceLoginsIds.isPresent()){
            for(Integer persistanceLoginId: persistanceLoginsIds.get()){
                PersistanceLoginEntity persistanceLogin = persistanceLoginRepository.findById(persistanceLoginId).orElse(null);
                if(persistanceLogin != null){
                    persistanceLoginsEntities.add(persistanceLogin);
                }
            }
        }
        returnValue.setPersistanceLogin(persistanceLoginsEntities);
        Optional<Integer> wishlistIdOptional = Optional.ofNullable(customerDto.getWishlistId());
        if(wishlistIdOptional.isPresent()){
            WishlistEntity wishlist = wishlistRepository.findById(wishlistIdOptional.get()).orElse(null);
            if(wishlist != null){
                returnValue.setWishlist(wishlist);
            }
        }
        return returnValue;
    }


}

