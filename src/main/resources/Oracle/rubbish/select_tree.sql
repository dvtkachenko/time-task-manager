SELECT     REL_TYPE_ID
                                       FROM ADMIN.DOC_OBJ_REL_TYPE
                                 START WITH REL_TYPE_ID = :ID
                                 CONNECT BY PRIOR REL_TYPE_ID =
                                                              PARENT_REL_TYPE