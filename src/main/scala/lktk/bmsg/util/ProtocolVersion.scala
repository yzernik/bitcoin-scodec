package lktk.bmsg.util

object ProtocolVersion {
  /**
    * network protocol versioning
    */
  val protocolVersion = 70015

  //! initial proto version, to be increased after version/verack negotiation
  val initProtoVersion = 209

  //! In this version, 'getheaders' was introduced.
  val getheadersVersion = 31800

  //! disconnect from peers older than this proto version
  val minPeerProtoVersion = getheadersVersion

  //! nTime field added to CAddress, starting with this version
  //! if possible, avoid requesting addresses nodes older than this
  val caddrTimeVersion = 31402

  //! BIP 0031, pong message, is enabled for all versions AFTER this one
  val bip0031Version = 60000

  //! "mempool" command, enhanced "getdata" behavior starts with this version
  val mempoolGdVersion = 60002

  // "filter*" commands are enabled from here to NO_BLOOM_VERSION
  val bloomVersion = 70001

  //! "filter*" commands are disabled without NODE_BLOOM after and including this
  //! version
  val noBloomVersion = 70011

  //! "sendheaders" command and announcing blocks with headers starts with this
  //! version
  val sendheadersVersion = 70012

  //! "feefilter" tells peers to filter invs to you by fee starts with this
  //! version
  val feefilterVersion = 70013

  //! short-id-based block download starts with this version
  val shortIdsBlocksVersion = 70014

  //! not banning for invalid compact blocks starts with this version
  val invalidCbNobanVersion = 70015

}
