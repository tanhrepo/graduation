import JSEncrypt from 'jsencrypt/bin/jsencrypt.min'

// 密钥对生成 http://web.chacuo.net/netrsakeypair

const publicKey = 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALYrDzOHx+sPKRol6KqoJgYHkIJnNnfq\n' +
  'Vq8RFSQw+qG79yP2IWH89KLIY+UNHsXQ56KHf31HSGfDMa6J9ZWj/70CAwEAAQ=='

const privateKey = 'MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAtisPM4fH6w8pGiXo\n' +
  'qqgmBgeQgmc2d+pWrxEVJDD6obv3I/YhYfz0oshj5Q0exdDnood/fUdIZ8Mxron1\n' +
  'laP/vQIDAQABAkBQq5TZvwFbKKWM/Mih+kCMXwExnj9SryWf9TKlikMTJ4di1Mrh\n' +
  '4VK8mbHF9XTxenKAS5Lumr5qRoiqr3R+TerJAiEA8qzpF2dT/aV9sUonAkkM0gIz\n' +
  'p4IFo9crheoftVO8uasCIQDAK6X6acQlNipEzj5G6FnYhrYCaIzXPZ1g64abzYRU\n' +
  'NwIhAIj5Heuuz9KIGUT7ZL8+ejUDNyKiGgrbRV6lwlX3uLtXAiEAqY3ZhHiNd8ey\n' +
  'w8K1cBrSKxmwva9BlSfoE/u7aPyMJUECIQCoosdodDqdgoe8ecBcMPqEzPo1wFNu\n' +
  '/Npjvs3ows1TxQ=='

// 加密
export function encrypt (txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPublicKey(publicKey) // 设置公钥
  return encryptor.encrypt(txt) // 对需要加密的数据进行加密
}

// 解密
export function decrypt (txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPrivateKey(privateKey)
  return encryptor.decrypt(txt)
}

