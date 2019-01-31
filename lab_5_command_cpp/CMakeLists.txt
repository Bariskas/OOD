cmake_minimum_required(VERSION 3.12)
project(lab_5)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(lab_5
        AbstractCommand.cpp
        AbstractCommand.h
        ChangeStringCOmmand.cpp
        ChangeStringCommand.h
        ConstDocumentItem.cpp
        ConstDocum`entItem.h
        Document.cpp
        Document.h
        DocumentItem.cpp
        DocumentItem.h
        Editor.cpp
        Editor.h
        History.cpp
        History.h
        ICommand.h
        IDocument.h
        IImage.h
        Image.cpp
        Image.h
        IParagraph.h
        main.cpp
        Menu.cpp
        Menu.h
        Paragraph.cpp
        Paragraph.h
        StringUtils.h)
