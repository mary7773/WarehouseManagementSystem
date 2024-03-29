USE [master]
GO
/****** Object:  Database [warehouseDB]    Script Date: 01-Mar-14 8:13:39 PM ******/
CREATE DATABASE [warehouseDB]


GO
ALTER DATABASE [warehouseDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [warehouseDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [warehouseDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [warehouseDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [warehouseDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [warehouseDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [warehouseDB] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [warehouseDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [warehouseDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [warehouseDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [warehouseDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [warehouseDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [warehouseDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [warehouseDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [warehouseDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [warehouseDB] SET  ENABLE_BROKER 
GO
ALTER DATABASE [warehouseDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [warehouseDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [warehouseDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [warehouseDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [warehouseDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [warehouseDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [warehouseDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [warehouseDB] SET RECOVERY FULL 
GO
ALTER DATABASE [warehouseDB] SET  MULTI_USER 
GO
ALTER DATABASE [warehouseDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [warehouseDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [warehouseDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [warehouseDB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [warehouseDB]
GO
USE [warehouseDB]
GO
/****** Object:  Sequence [dbo].[invoice_number_seq]    Script Date: 01-Mar-14 8:13:39 PM ******/
CREATE SEQUENCE [dbo].[invoice_number_seq] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE  20 
GO
USE [warehouseDB]
GO
/****** Object:  Sequence [dbo].[num_order_seq]    Script Date: 01-Mar-14 8:13:39 PM ******/
CREATE SEQUENCE [dbo].[num_order_seq] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE  20 
GO
/****** Object:  Table [dbo].[applicationlog]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[applicationlog](
	[Logid] [int] NOT NULL,
	[UserId] [int] NOT NULL,
	[log] [text] NOT NULL,
	[date_login] [datetime] NOT NULL,
	[date_logout] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Logid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Brands]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brands](
	[BrandId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](60) NOT NULL,
 CONSTRAINT [PK__Brands__DAD4F05E0AB61A1A] PRIMARY KEY CLUSTERED 
(
	[BrandId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Categories]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[CategoryId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](60) NOT NULL,
 CONSTRAINT [PK__Categori__19093A0B5F7B30EB] PRIMARY KEY CLUSTERED 
(
	[CategoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Cities]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cities](
	[CityID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[CountryId] [int] NOT NULL,
 CONSTRAINT [PK_Cities] PRIMARY KEY CLUSTERED 
(
	[CityID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Countries]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Countries](
	[CountryId] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Countries] PRIMARY KEY CLUSTERED 
(
	[CountryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Invoic_Order]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoic_Order](
	[InvoiceID] [int] NOT NULL,
	[OrderID] [int] NOT NULL,
 CONSTRAINT [PK_Invoic_Order] PRIMARY KEY CLUSTERED 
(
	[InvoiceID] ASC,
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[InvoiceId] [int] IDENTITY(1,1) NOT NULL,
	[date_invoice] [datetime] NOT NULL,
	[Invoice_Number] [int] NOT NULL,
 CONSTRAINT [PK__Invoice__D796AAB577F3B4F8] PRIMARY KEY CLUSTERED 
(
	[InvoiceId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[orders]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[OrderId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[orderDate] [datetime] NOT NULL,
	[PaymentID] [int] NULL,
	[ProductId] [int] NULL,
	[InvoiceId] [int] NULL,
	[orderSum] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK__orders__C3905BCFABAC9C60] PRIMARY KEY CLUSTERED 
(
	[OrderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Payment]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[PaymentId] [int] IDENTITY(1,1) NOT NULL,
	[Type_PaymentID] [int] NOT NULL,
	[UserId] [int] NOT NULL,
	[InvoiceId] [int] NULL,
 CONSTRAINT [PK__Payment__9B556A3871ABEBE4] PRIMARY KEY CLUSTERED 
(
	[PaymentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Products]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[BrandId] [int] IDENTITY(1,1) NOT NULL,
	[ProductId] [int] NOT NULL,
	[Name] [nvarchar](60) NOT NULL,
	[Price] [float] NOT NULL,
	[CategoryId] [int] NOT NULL,
	[Quantity] [float] NOT NULL,
	[Image] [image] NULL,
 CONSTRAINT [PK__Products__B40CC6CDD9E42DEE] PRIMARY KEY CLUSTERED 
(
	[ProductId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TypePayment]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TypePayment](
	[TypePaymId] [int] NOT NULL,
	[type_payment] [nvarchar](12) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TypePaymId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Users]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[Code] [varchar](255) NULL,
	[FirstName] [varchar](255) NULL,
	[LastName] [varchar](255) NULL,
	[Company] [varchar](255) NULL,
	[MOL] [varchar](255) NULL,
	[Address] [varchar](255) NULL,
	[Address2] [varchar](255) NULL,
	[Phone] [varchar](255) NULL,
	[Fax] [varchar](255) NULL,
	[eMail] [varchar](255) NULL,
	[TaxNo] [varchar](255) NULL,
	[Bulstat] [varchar](255) NULL,
	[BankName] [varchar](255) NULL,
	[BankCode] [varchar](255) NULL,
	[BankAcct] [varchar](255) NULL,
	[BankVATName] [varchar](255) NULL,
	[BankVATCode] [varchar](255) NULL,
	[BankVATAcct] [varchar](255) NULL,
	[PriceGroup] [int] NULL,
	[Discount] [float] NULL,
	[Type] [int] NULL,
	[CityID] [int] NULL,
	[IsVeryUsed] [int] NULL,
	[UserRealTime] [datetime] NULL,
	[IsDeleted] [bit] NULL,
	[CardNumber] [int] NULL,
	[UserName] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[UserGroupID] [int] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UsersGroups]    Script Date: 01-Mar-14 8:13:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UsersGroups](
	[UsersGroupID] [int] IDENTITY(1,1) NOT NULL,
	[GroupName] [nvarchar](30) NOT NULL,
 CONSTRAINT [PK__UsersGro__FF10EEC743D2644A] PRIMARY KEY CLUSTERED 
(
	[UsersGroupID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Cities] ON 

INSERT [dbo].[Cities] ([CityID], [name], [CountryId]) VALUES (1, N'Plovdiv', 1)
INSERT [dbo].[Cities] ([CityID], [name], [CountryId]) VALUES (2, N'Sofia', 1)
INSERT [dbo].[Cities] ([CityID], [name], [CountryId]) VALUES (3, N'Karlovo', 1)
INSERT [dbo].[Cities] ([CityID], [name], [CountryId]) VALUES (4, N'London', 3)
INSERT [dbo].[Cities] ([CityID], [name], [CountryId]) VALUES (5, N'Birmingam', 3)
INSERT [dbo].[Cities] ([CityID], [name], [CountryId]) VALUES (6, N'Berlin', 2)
INSERT [dbo].[Cities] ([CityID], [name], [CountryId]) VALUES (7, N'Koblenz', 2)
INSERT [dbo].[Cities] ([CityID], [name], [CountryId]) VALUES (9, N'NewYork', 4)
SET IDENTITY_INSERT [dbo].[Cities] OFF
SET IDENTITY_INSERT [dbo].[Countries] ON 

INSERT [dbo].[Countries] ([CountryId], [name]) VALUES (1, N'Bulgaria')
INSERT [dbo].[Countries] ([CountryId], [name]) VALUES (2, N'Germany')
INSERT [dbo].[Countries] ([CountryId], [name]) VALUES (3, N'UK')
INSERT [dbo].[Countries] ([CountryId], [name]) VALUES (4, N'USA')
SET IDENTITY_INSERT [dbo].[Countries] OFF
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([UserId], [Code], [FirstName], [LastName], [Company], [MOL], [Address], [Address2], [Phone], [Fax], [eMail], [TaxNo], [Bulstat], [BankName], [BankCode], [BankAcct], [BankVATName], [BankVATCode], [BankVATAcct], [PriceGroup], [Discount], [Type], [CityID], [IsVeryUsed], [UserRealTime], [IsDeleted], [CardNumber], [UserName], [Password], [UserGroupID]) VALUES (1, N'1', N'Todor', N'Neykov', N'Skytek', N'', N'', N'', N'', N'', N'', N'', N'', NULL, N'', N'', NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, N'Ԁt琄獥t', N'Ԁt㄁', NULL)
INSERT [dbo].[Users] ([UserId], [Code], [FirstName], [LastName], [Company], [MOL], [Address], [Address2], [Phone], [Fax], [eMail], [TaxNo], [Bulstat], [BankName], [BankCode], [BankAcct], [BankVATName], [BankVATCode], [BankVATAcct], [PriceGroup], [Discount], [Type], [CityID], [IsVeryUsed], [UserRealTime], [IsDeleted], [CardNumber], [UserName], [Password], [UserGroupID]) VALUES (2, NULL, N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', NULL, N'', N'', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, N'test', N'1', NULL)
SET IDENTITY_INSERT [dbo].[Users] OFF
SET IDENTITY_INSERT [dbo].[UsersGroups] ON 

INSERT [dbo].[UsersGroups] ([UsersGroupID], [GroupName]) VALUES (1, N'Administrators')
INSERT [dbo].[UsersGroups] ([UsersGroupID], [GroupName]) VALUES (2, N'Clients')
SET IDENTITY_INSERT [dbo].[UsersGroups] OFF
/****** Object:  Index [IX_Invoic_Order]    Script Date: 01-Mar-14 8:13:39 PM ******/
CREATE NONCLUSTERED INDEX [IX_Invoic_Order] ON [dbo].[Invoic_Order]
(
	[InvoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[applicationlog] ADD  DEFAULT (getdate()) FOR [date_login]
GO
ALTER TABLE [dbo].[applicationlog] ADD  DEFAULT (getdate()) FOR [date_logout]
GO
ALTER TABLE [dbo].[Invoice] ADD  CONSTRAINT [DF__Invoice__date_in__4AB81AF0]  DEFAULT (getdate()) FOR [date_invoice]
GO
ALTER TABLE [dbo].[orders] ADD  CONSTRAINT [DF__orders__date__5441852A]  DEFAULT (getdate()) FOR [orderDate]
GO
ALTER TABLE [dbo].[Payment] ADD  CONSTRAINT [DF__Payment__UserId__2D27B809]  DEFAULT ((-1)) FOR [UserId]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF__Products__Quanti__4222D4EF]  DEFAULT ((0.00)) FOR [Quantity]
GO
ALTER TABLE [dbo].[Cities]  WITH CHECK ADD  CONSTRAINT [FK_Cities_Countries] FOREIGN KEY([CountryId])
REFERENCES [dbo].[Countries] ([CountryId])
GO
ALTER TABLE [dbo].[Cities] CHECK CONSTRAINT [FK_Cities_Countries]
GO
ALTER TABLE [dbo].[Invoic_Order]  WITH CHECK ADD  CONSTRAINT [FK_Invoic_Order_Invoice] FOREIGN KEY([InvoiceID])
REFERENCES [dbo].[Invoice] ([InvoiceId])
GO
ALTER TABLE [dbo].[Invoic_Order] CHECK CONSTRAINT [FK_Invoic_Order_Invoice]
GO
ALTER TABLE [dbo].[Invoic_Order]  WITH CHECK ADD  CONSTRAINT [FK_Invoic_Order_orders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[orders] ([OrderId])
GO
ALTER TABLE [dbo].[Invoic_Order] CHECK CONSTRAINT [FK_Invoic_Order_orders]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_Payment] FOREIGN KEY([PaymentID])
REFERENCES [dbo].[Payment] ([PaymentId])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_orders_Payment]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_Products] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Products] ([ProductId])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_orders_Products]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_Users] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([UserId])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_orders_Users]
GO
ALTER TABLE [dbo].[Payment]  WITH CHECK ADD  CONSTRAINT [FK_Payment_TypePayment] FOREIGN KEY([Type_PaymentID])
REFERENCES [dbo].[TypePayment] ([TypePaymId])
GO
ALTER TABLE [dbo].[Payment] CHECK CONSTRAINT [FK_Payment_TypePayment]
GO
ALTER TABLE [dbo].[Payment]  WITH CHECK ADD  CONSTRAINT [FK_Payment_Users] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([UserId])
GO
ALTER TABLE [dbo].[Payment] CHECK CONSTRAINT [FK_Payment_Users]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Brands] FOREIGN KEY([BrandId])
REFERENCES [dbo].[Brands] ([BrandId])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Brands]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Categories] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Categories] ([CategoryId])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Categories]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Cities] FOREIGN KEY([CityID])
REFERENCES [dbo].[Cities] ([CityID])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Cities]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_UsersGroups] FOREIGN KEY([UserGroupID])
REFERENCES [dbo].[UsersGroups] ([UsersGroupID])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_UsersGroups]
GO
USE [master]
GO
ALTER DATABASE [warehouseDB] SET  READ_WRITE 
GO
